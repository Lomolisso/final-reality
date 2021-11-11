package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.controller.handlers.*;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.controller.phases.subphases.StartGamePhase;
import com.github.cc3002.finalreality.gui.*;
import com.github.cc3002.finalreality.gui.scenes.EnemyTurnScene;
import com.github.cc3002.finalreality.gui.scenes.PlayerBeginTurnScene;
import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.*;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.*;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.*;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.*;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GameController {
    private Phase phase;
    protected LinkedList<ICharacter> playerCharacters = new LinkedList<>();
    protected LinkedList<ICharacter> enemies = new LinkedList<>();
    protected BlockingQueue<ICharacter> turnsQueue  = new LinkedBlockingQueue<>();
    protected LinkedList<IWeapon> inventory = new LinkedList<>();
    protected EnemyTurnHandler enemyTurnHandler = new EnemyTurnHandler(this);
    protected PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler(this);
    protected PlayerHealthPointsHandler playerHealthPointsHandler = new PlayerHealthPointsHandler(this);
    protected EnemyHealthPointsHandler enemyHealthPointsHandler = new EnemyHealthPointsHandler(this);
    private View view = null;
    private boolean testing;
    private int gameStatus = 0; // -1 means the player lost the game, 0 means the game hasn't ended yet, 1 means the player won the game.

    /**
     * Constructor of the class. It is used only when we are not testing.
     */
    public GameController() {
        this.testing = false;
    }

    /**
     * Constructor of the class
     * @param testing
     */
    public GameController(boolean testing) {
        this.testing = testing;
    }

    /**
     * This method is called by the view in order to set up the game.
     */
    public void gameSetUp(){
        setPhase(new StartGamePhase("StartGamePhase"));
        createEnemy("Morgaroth",80,250,50,120,"img\\Morgaroth.png");
        createEnemy("Ghazbaran",40,100,25,70,"img\\Ghazbaran.png");
        createEnemy("Apocalypse",60,200,40,80,"img\\Apocalypse.png");
        createEnemy("Latrivan",30,150,30,60,"img\\Latrivan.png");
        createEnemy("Verminor",30,130,25,70,"img\\Verminor.png");

        createStaff("Staff of Defiance",200,100);
        createKnife("The Kingslayer Knife",50,20);
        createSword("Soulcutter Sword",70,40);
        createAxe("Energy Headchopper",90,60);
        createBow("Doom Bow",70,30);
    }

    /**
     * Returns the status of the game. As it was said before, -1 means the player lost the game, 0 means the game hasn't ended yet,
     * 1 means the player won the game.
     * @return
     */
    public int getGameStatus(){
        return gameStatus;
    }

    /**
     * Sets the game status as 1.
     */
    public void playerWins(){
        gameStatus = 1;
    }

    /**
     * This method is called when all the playerCharacters are dead, in other words,
     * it is called when the "alive-list" of the playerCharacters is empty.
     */
    public void playerLoses() {
        gameStatus=-1;
    }

    /**
     * This method is called every time a playerCharacter reach 0 health points, it
     * takes him out of the turns queue and the "alive-list" of playerCharacters.
     * @param playerCharacter
     */
    public void onPlayerDeath(ICharacter playerCharacter){
        playerCharacters.remove(playerCharacter);
        turnsQueue.remove(playerCharacter);
        if(playerCharacters.isEmpty()){
            toEndGamePhase();
            playerLoses();
        }
    }

    /**
     * This method is called every time an enemy reach 0 health points, it
     * takes him out of the turns queue and the "alive-list" of enemies.
     * @param enemy
     */
    public void onEnemyDeath(ICharacter enemy){
        enemies.remove(enemy);
        turnsQueue.remove(enemy);
        if(enemies.isEmpty()){
            toEndGamePhase();
            tryPlayerWins();
        }
    }

    /**
     * This method starts the turn of an ICharacter, note that if we are testing then
     * the method waits turnsDelay ms before calling character.notifyTurn(). If we are not
     * then turnsDelay is 0 and it notifies instantly.
     * @param character
     */
    public void runTurn(ICharacter character){
        character.notifyTurn();
    }

    /**
     * This method ends the turn of an ICharacter.
     * @param character
     */
    public void endTurn(ICharacter character){
        turnsQueue.remove(character);
        waitTurn(character);
        checkNextInQueue(); }

    /**
     * This method checks if there are at least one Character in the turns queue,
     * if there are, it calls the runTurn() method for the head of the queue.
     */
    public void checkNextInQueue(){
        if(!turnsQueue.isEmpty()){
            runTurn(turnsQueue.peek()); } }

    /**
     * This method is called when an enemy starts his turn.
     * @param enemy
     */
    public void onEnemyTurn(ICharacter enemy){
        toEnemyBeginTurnPhase();
        if(!testing) {                                                              // IF WE ARE TESTING WE IGNORE THIS BECAUSE ITS RELATED TO THE VIEW
            EnemyTurnScene enemyTurnScene = new EnemyTurnScene(view);
            try {
                enemyTurnScene.begin();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        }

    /**
     * This method is called when a playerCharacter starts his turn.
     * @param playerCharacter
     */
    public void onPlayerCharacterTurn(ICharacter playerCharacter) {
        toPlayerBeginTurnPhase();
        if(!testing) {
            PlayerBeginTurnScene playerBeginTurnScene = new PlayerBeginTurnScene(view);     // IF WE ARE TESTING WE IGNORE THIS BECAUSE ITS RELATED TO THE VIEW
            try {
                playerBeginTurnScene.begin();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        }

    /**
     * This method receives an ICharacter and adds it to the turns queue. If the turns queue
     * was empty before adding the ICharacter, the method will call the runTurn() method in
     * order to begin his turn.
     * @param character
     */
    public void addToQueue(ICharacter character) {
        boolean alive = playerCharacters.contains(character)  || enemies.contains(character);
        if(!alive){return;}
        boolean isEmpty = turnsQueue.isEmpty();
        character.getScheduledExecutor().shutdown();
        turnsQueue.add(character);
            if (isEmpty){
                runTurn(character);
            }
    }

    /**
     * This method makes an ICharacter wait a specific delay before coming back to the turns queue.
     * @param character
     */
    public void waitTurn(ICharacter character){
        character.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        long delay = character.calculateWaitPeriod();
        Runnable command = () -> this.addToQueue(character);
        character.getScheduledExecutor().schedule(command, delay, TimeUnit.SECONDS);
    }

    /**
     * This method starts the turns system.
     */
    public void startTurns(){
        for(ICharacter playerCharacter : playerCharacters){
            waitTurn(playerCharacter);
        }
        for(ICharacter enemy : enemies){
            waitTurn(enemy);
        }
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the health points and defense points, it returns an object of the class BlackMage with
     * those characteristics.
     * @param name
     * @param healthPoints
     * @param defensePoints
     * @return object instance of BlackMage class.
     */
    public BlackMage createBlackMage(String name, int healthPoints, int defensePoints){
        BlackMage blackmage = new BlackMage(name,healthPoints,healthPoints,defensePoints);
        blackmage.addListenerPCSTurn(playerTurnHandler);
        blackmage.addListenerPCSHealth(playerHealthPointsHandler);
        playerCharacters.add(blackmage);
        return blackmage;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the health points and defense points, it returns an object of the class WhiteMage with
     * those characteristics.
     * @param name
     * @param healthPoints
     * @param defensePoints
     * @return object instance of WhiteMage class.
     */
    public WhiteMage createWhiteMage(String name, int healthPoints, int defensePoints){
        WhiteMage whitemage = new WhiteMage(name,healthPoints,healthPoints,defensePoints);
        whitemage.addListenerPCSTurn(playerTurnHandler);
        whitemage.addListenerPCSHealth(playerHealthPointsHandler);
        playerCharacters.add(whitemage);
        return whitemage;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the health points and defense points, it returns an object of the class Engineer with
     * those characteristics.
     * @param name
     * @param healthPoints
     * @param defensePoints
     * @return object instance of Engineer class.
     */
    public Engineer createEngineer(String name, int healthPoints, int defensePoints){
        Engineer engineer = new Engineer(name,healthPoints,healthPoints,defensePoints);
        engineer.addListenerPCSTurn(playerTurnHandler);
        engineer.addListenerPCSHealth(playerHealthPointsHandler);
        playerCharacters.add(engineer);
        return engineer;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the health points and defense points, it returns an object of the class Knight with
     * those characteristics.
     * @param name
     * @param healthPoints
     * @param defensePoints
     * @return object instance of Knight class.
     */
    public Knight createKnight(String name, int healthPoints, int defensePoints){
        Knight knight = new Knight(name,healthPoints,healthPoints,defensePoints);
        knight.addListenerPCSTurn(playerTurnHandler);
        knight.addListenerPCSHealth(playerHealthPointsHandler);
        playerCharacters.add(knight);
        return knight;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the health points and defense points, it returns an object of the class Thief with
     * those characteristics.
     * @param name
     * @param healthPoints
     * @param defensePoints
     * @return object instance of Thief class.
     */
    public Thief createThief(String name, int healthPoints, int defensePoints){
        Thief thief = new Thief(name,healthPoints,healthPoints,defensePoints);
        thief.addListenerPCSTurn(playerTurnHandler);
        thief.addListenerPCSHealth(playerHealthPointsHandler);
        playerCharacters.add(thief);
        return thief;
    }

    /**
     * This method receives a String that represents a name and four ints that represent
     * the weight, health points, defense points and damage of an enemy, it returns an object
     * of the class Enemy with those characteristics.
     * @param name
     * @param weight
     * @param healthPoints
     * @param defensePoints
     * @param damage
     * @return object instance of Enemy class.
     */
    public Enemy createEnemy(String name, int weight, int healthPoints, int defensePoints, int damage, String skin){
        Enemy enemy = new Enemy(name,weight,healthPoints,healthPoints,defensePoints,damage,skin);
        enemy.addListenerPCSTurn(enemyTurnHandler);
        enemy.addListenerPCSHealth(enemyHealthPointsHandler);
        enemies.add(enemy);
        return enemy;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the damage and the weight of a weapon. It returns an object of the class Axe with those
     * characteristics.
     * @param name
     * @param damage
     * @param weight
     * @return object instance of Axe class.
     */
    public Axe createAxe(String name, int damage, int weight){
        Axe axe = new Axe(name,damage,weight);
        pickUpItem(axe);
        return axe;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the damage and the weight of a weapon. It returns an object of the class Bow with those
     * characteristics.
     * @param name
     * @param damage
     * @param weight
     * @return object instance of Bow class.
     */
    public Bow createBow(String name, int damage, int weight){
        Bow bow = new Bow(name,damage,weight);
        pickUpItem(bow);
        return bow;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the damage and the weight of a weapon. It returns an object of the class Knife with those
     * characteristics.
     * @param name
     * @param damage
     * @param weight
     * @return object instance of Knife class.
     */
    public Knife createKnife(String name, int damage, int weight){
        Knife knife = new Knife(name,damage,weight);
        pickUpItem(knife);
        return knife;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the damage and the weight of a weapon. It returns an object of the class Staff with those
     * characteristics.
     * @param name
     * @param damage
     * @param weight
     * @return object instance of Staff class.
     */
    public Staff createStaff(String name, int damage, int weight){
        Staff staff = new Staff(name,damage,weight);
        pickUpItem(staff);
        return staff;
    }

    /**
     * This method receives a String that represents a name and two ints that represent
     * the damage and the weight of a weapon. It returns an object of the class Sword with those
     * characteristics.
     * @param name
     * @param damage
     * @param weight
     * @return object instance of Sword class.
     */
    public Sword createSword(String name, int damage, int weight){
        Sword sword = new Sword(name,damage,weight);
        pickUpItem(sword);
        return sword;
    }
    /**
     * This method pickups an object and stores it in the inventory.
     * (At the moment it only pickups weapons.)
     * @param item
     */
    public void pickUpItem(IWeapon item){
        inventory.add(item);
    }

    /**
     * This method receives an item and removes it from the inventory.
     * @param //item
     */
    public void removeItem(IWeapon item){
        inventory.remove(item);
    }

    /**
     * This method returns the inventory of the player.
     * @return
     */
    public LinkedList<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * This method receives an IPlayerCharacter and an IWeapon, if it is possible,
     * it equips the weapon to the character.
     * @param playerCharacter
     * @param weapon
     */
    public int equipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon){
        IWeapon previousWeapon = playerCharacter.getEquippedWeapon();
        if(previousWeapon!=null){
            pickUpItem(previousWeapon);
        }
        int actionId = playerCharacter.equipWeapon(weapon);
        if(actionId==-1){
            return 0;   // VIEW MESSAGE: "You can't equip that weapon to that class!"
        }
        removeItem(weapon);
        return 1;
    }

    /**
     * This method receives a Linked List of possible ICharacters and returns
     * a random member of it.
     */
    public ICharacter getRandomPlayerCharacter(){
        Random rand = new Random();
        int index = rand.nextInt(playerCharacters.size());
        ICharacter character = playerCharacters.get(index);
        return character;
    }

    /**
     * This method receives two ICharacters, the attacker and the target, it makes
     * the attacker attack the target. The method returns the inflicted damage.
     * @param attacker
     * @param target
     */
    public int actionAttackCharacter(ICharacter attacker, ICharacter target){
        int inflictedDamage = attacker.attackCharacter(target);
        return inflictedDamage;
    }


    /**
     * This method receives an attacker (ICharacter) and makes him attack a random member of the playerCharacter
     * LinkedList of the controller. It returns a LinkedList with the information related to de action, the first
     * element is the inflicted damage and the second one, the name of the target.
     * @param attacker
     * @return
     */
    public LinkedList actionAttackRandomPlayerCharacter(ICharacter attacker){
        ICharacter target  = getRandomPlayerCharacter();
        int inflictedDamage = actionAttackCharacter(attacker,target);
        LinkedList info = new LinkedList();
        info.add(inflictedDamage);
        info.add(target.getName());
        return info;
    }

    /**
     * This method returns the "alive-list" of playerCharacters.
     * @return
     */
    public LinkedList<ICharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    /**
     * This method returns the "alive-list" of enemies.
     * @return
     */
    public LinkedList<ICharacter> getEnemies() {
        return enemies;
    }

    /**
     * This method returns the turnsQueue of the controller.
     * @return
     */
    public BlockingQueue<ICharacter> getTurnsQueue() {
        return turnsQueue;
    }

    /**
     * This method sets the turns queue as an specific input.
     * @param turnsQueue
     */
    public void setTurnsQueue(BlockingQueue<ICharacter> turnsQueue) {
        this.turnsQueue = turnsQueue;
    }

    /**
     * This method returns the phase of the controller.
     * @return
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * This method sets the phase of the controller according to the state pattern.
     * @param phase
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * Changes the current phase to
     */
    public void toPlayerBeginTurnPhase(){
        try {
            phase.toPlayerBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * Changes the current phase to
     */
    public void toEquipWeaponPhase(){
        try {
            phase.toEquipWeaponPhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * Changes the current phase to SelectAttackTargetPhase.
     */
    public void toSelectAttackTargetPhase(){
        try {
            phase.toSelectAttackTargetPhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * Changes the current phase to EnemyBeginTurnPhase.
     */
    public void toEnemyBeginTurnPhase(){
        try {
            phase.toEnemyBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * Changes the current phase to EndTurnPhase.
     */
    public void toEndTurnPhase(){
        try {
            phase.toEndTurnPhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * Changes the current phase to EndGamePhase
     */
    public void toEndGamePhase(){
        try {
            phase.toEndGamePhase();
        } catch (InvalidTransitionException e) {
            return;
        }
    }

    /**
     * This method tries to call the startTurns method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public void tryStartTurns(){
        try {
            phase.startTurns();
        } catch (InvalidMethodCalledException e) {
            return;
        }
    }

    /**
     * This method tries to call the playerWins method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public void tryPlayerWins(){
        try {
            phase.playerWins();
        } catch (InvalidMethodCalledException e) {
            return;
        }
    }

    /**
     * This method tries to call the playerLoses method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public void tryPlayerLoses(){
        try {
            phase.playerLoses();
        } catch (InvalidMethodCalledException e) {
            return;
        }
    }

    /**
     * This method tries to call the actionAttackRandomPlayerCharacter method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public LinkedList tryActionAttackRandomPlayerCharacter(ICharacter target){
        try {
            return phase.actionAttackRandomPlayerCharacter(target);
        } catch (InvalidMethodCalledException e) {
            return null;
        }
    }

    /**
     * This method tries to call the actionAttackCharacter method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public int tryActionAttackCharacter(ICharacter attacker, ICharacter target){
        try {
            return phase.actionAttackCharacter(attacker,target);
        } catch (InvalidMethodCalledException e) {
            return -1;
        }
    }

    /**
     * This method tries to call the equipWeapon method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public int tryEquipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon){
        try {
            return phase.equipWeapon(playerCharacter,weapon);
        } catch (InvalidMethodCalledException e) {
            return -1;
        }
    }

    /**
     * This method tries to call the endTurn method of the controller,
     * it will call the method only if the phase of the controller is the correct one.
     */
    public void tryEndTurn(ICharacter character){
        try {
            phase.endTurn(character);
        } catch (InvalidMethodCalledException e) {
            return;
        }
    }

    /**
     * This method returns a linked list with the names of all the playerCharacters that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<String> getNamesPlayerCharactersAlive(){
        LinkedList<String> names = new LinkedList<>();
        for(ICharacter playerCharacter : playerCharacters){
            names.add(playerCharacter.getName());
        }
        return names;
    }

    /**
     * This method returns a linked list with the names of all the enemie that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<String> getNamesEnemiesAlive(){
        LinkedList<String> names = new LinkedList<>();
        for(ICharacter enemy : enemies){
            names.add(enemy.getName());
        }
        return names;
    }

    /**
     * This method returns a linked list with the hp of all the playerCharacters that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getCurrentHpPlayerCharactersAlive(){
        LinkedList<Integer> hps = new LinkedList<>();
        for(ICharacter playerCharacter : playerCharacters){
            hps.add(playerCharacter.getCurrentHealthPoints());
        }
        return hps;
    }

    /**
     * This method returns a linked list with the hp of all the enemies that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getCurrentHpEnemiesAlive(){
        LinkedList<Integer> hps = new LinkedList<>();
        for(ICharacter enemy : enemies){
            hps.add(enemy.getCurrentHealthPoints());
        }
        return hps;
    }

    /**
     * This method returns a linked list with the def of all the playerCharacters that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getDefPlayerCharactersAlive(){
        LinkedList<Integer> hps = new LinkedList<>();
        for(ICharacter playerCharacter : playerCharacters){
            hps.add(playerCharacter.getDefensePoints());
        }
        return hps;
    }

    /**
     * This method returns a linked list with the def of all the enemies that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getDefEnemiesAlive(){
        LinkedList<Integer> defs = new LinkedList<>();
        for(ICharacter enemy : enemies){
            defs.add(enemy.getDefensePoints());
        }
        return defs;
    }

    /**
     * This method returns a linked list with the atk of all the playerCharacters that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getAtkPlayerCharactersAlive(){
        LinkedList<Integer> atks = new LinkedList<>();
        for(ICharacter playerCharacter : playerCharacters){
            IPlayerCharacter cast = (IPlayerCharacter) playerCharacter;
            IWeapon equippedWeapon = cast.getEquippedWeapon();
            if(equippedWeapon!=null) {
                atks.add(equippedWeapon.getDamage());
            }
            else{
                atks.add(0);
            }
        }
        return atks;
    }

    /**
     * This method returns a linked list with the atk of all the enemies that are alive
     * at the moment of calling.
     * @return
     */
    public LinkedList<Integer> getAtkEnemiesAlive(){
        LinkedList<Integer> atks = new LinkedList<>();
        for(ICharacter enemy : enemies){
            Enemy cast = (Enemy) enemy;
            atks.add(cast.getDamage());
        }
        return atks;
    }

    /**
     * This method returns a linked list with all the paths of the skins of all the playerCharcters that are alive at the moment of calling.
     * @return
     */
    public LinkedList<String> getPlayerCharacterSkins(){
        LinkedList<String> skins = new LinkedList<>();
        for(ICharacter playerCharacter : playerCharacters){
            skins.add(playerCharacter.getSkin());
        }
        return skins;
    }

    /**
     * This method returns a linked list with all the paths of all the skins of all the enemies that are alive at the moment of calling.
     * @return
     */
    public LinkedList<String> getEnemiesSkins(){
        LinkedList<String> skins = new LinkedList<>();
        for(ICharacter enemy : enemies){
            skins.add(enemy.getSkin());
        }
        return skins;
    }

    /**
     * This method returns the name of the weapon that the IPlayerCharacter on turn has.
     * @return
     */
    public String getNameWeaponPlayerInTurn(){
        IPlayerCharacter cast = (IPlayerCharacter) turnsQueue.peek();
        IWeapon equippedWeapon = cast.getEquippedWeapon();
        if (equippedWeapon!=null) {
            return cast.getEquippedWeapon().getName();
        }
        return "";
    }

    /**
     * This method returns the name of the weapon that the ICharacter on turn has.
     * @return
     */
    public String getNameCharacterInTurn(){
        ICharacter character = turnsQueue.peek();
        return character.getName();
    }

    /**
     * This method returns a linked list with all the names of all the weapons in the inventory.
     * @return
     */
    public LinkedList<String> getNameInventoryWeapons(){
        LinkedList<String> names  = new LinkedList();
        for(IWeapon weapon : inventory){
            names.add(weapon.getName());
        }
        return names;
    }

    /**
     * This method returns a linked list with all the atk of all the weapons in the inventory.
     * @return
     */
    public LinkedList<Integer> getAtkInventoryWeapons(){
        LinkedList<Integer> atks  = new LinkedList();
        for(IWeapon weapon : inventory){
            atks.add(weapon.getDamage());
        }
        return atks;
    }

    /**
     * This method returns a linked list with all the weight of all the weapons in the inventory.
     * @return
     */
    public LinkedList<Integer> getWeightInventoryWeapons(){
        LinkedList<Integer> weights  = new LinkedList();
        for(IWeapon weapon : inventory){
            weights.add(weapon.getWeight());
        }
        return weights;
    }

    /**
     * This method returns a linked list with all the paths to the skin of each weapon in the inventory.
     * @return
     */
    public LinkedList<String> getSkinInventoryWeapons(){
        LinkedList<String> skins = new LinkedList<>();
        for(IWeapon weapon : inventory){
            skins.add(weapon.getSkin());
        }
        return skins;
    }

    /**
     * This method returns the IPlayerCharacter which is in his turn.
     * @return
     */
    public IPlayerCharacter getPlayerCharacterInTurn(){
        IPlayerCharacter character = (IPlayerCharacter) getTurnsQueue().peek();
        return character;
    }

    /**
     * Sets the view as an specific input.
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * This method returns the view of the controller.
     * @return
     */
    public View getView(){
        return view;
    }
}
