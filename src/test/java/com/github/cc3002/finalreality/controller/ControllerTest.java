package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.controller.phases.subphases.*;
import com.github.cc3002.finalreality.gui.View;
import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.*;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.*;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest{
   GameController testController = new GameController(true);
   BlackMage testBlackMage;
   WhiteMage testWhiteMage;
   Engineer testEngineer;
   Knight testKnight;
   Thief testThief;
   LinkedList<ICharacter> testPlayerCharacters;
   Enemy testEnemy;
   LinkedList<ICharacter> testEnemies;
   Axe testAxe;
   Bow testBow;
   Knife testKnife;
   Staff testStaff;
   Sword testSword;

   @BeforeEach
   void setUp(){
       testBlackMage = testController.createBlackMage("black mage",100,0);
       testWhiteMage = testController.createWhiteMage("white mage",100,0);
       testEngineer = testController.createEngineer("engineer",100,0);
       testKnight = testController.createKnight("knight",100,0);
       testThief = testController.createThief("thief",100,0);
       testPlayerCharacters = testController.getPlayerCharacters();


       testEnemy = testController.createEnemy("enemy",100,100,0,100,"img\\Apocalypse.png");
       testEnemies = testController.getEnemies();

       testAxe = testController.createAxe("axe",100,100);
       testBow = testController.createBow("bow",100,100);
       testKnife = testController.createKnife("knife",100,100);
       testSword = testController.createSword("sword",100,100);
       testStaff = testController.createStaff("staff",100,100);
   }

    /**
     * This test checks if the create methods of the controller work properly.
     */
   @Test
   void controllerCreateTest(){
       BlackMage expectedBlackMage = new BlackMage("black mage",100,100,0);
       WhiteMage expectedWhiteMage = new WhiteMage("white mage",100,100,0);
       Engineer expectedEngineer = new Engineer("engineer", 100, 100,0);
       Knight expectedKnight = new Knight("knight", 100, 100,0);
       Thief expectedThief = new Thief("thief",100,100,0);

       LinkedList<ICharacter> expectedPlayerCharacters = new LinkedList<ICharacter>();
       expectedPlayerCharacters.add(expectedBlackMage);
       expectedPlayerCharacters.add(expectedWhiteMage);
       expectedPlayerCharacters.add(expectedEngineer);
       expectedPlayerCharacters.add(expectedKnight);
       expectedPlayerCharacters.add(expectedThief);

       Enemy expectedEnemy = new Enemy("enemy",100,100,100,0,100,"img\\Apocalypse.png");

       LinkedList<ICharacter> expectedEnemies = new LinkedList<ICharacter>();
       expectedEnemies.add(expectedEnemy);

       Axe expectedAxe = new Axe("axe",100,100);
       Bow expectedBow = new Bow("bow",100,100);
       Knife expectedKnife = new Knife("knife",100,100);
       Staff expectedStaff = new Staff("staff",100,100);
       Sword expectedSword = new Sword("sword",100,100);

       assertEquals(expectedBlackMage,testBlackMage);
       assertEquals(expectedWhiteMage,testWhiteMage);
       assertEquals(expectedEngineer,testEngineer);
       assertEquals(expectedKnight,testKnight);
       assertEquals(expectedThief,testThief);
       assertEquals(expectedPlayerCharacters,testPlayerCharacters);

       assertEquals(expectedEnemy,testEnemy);
       assertEquals(expectedEnemies,testEnemies);

       assertEquals(expectedAxe,testAxe);
       assertEquals(expectedBow,testBow);
       assertEquals(expectedKnife,testKnife);
       assertEquals(expectedStaff,testStaff);
       assertEquals(expectedSword,testSword);
   }

    /**
     * This test checks if the inventory related methods of the controller work properly.
     */
   @Test
    void inventoryTest(){
       LinkedList<IWeapon> expectedInventory = new LinkedList<>();
       expectedInventory.add(testAxe);
       expectedInventory.add(testBow);
       expectedInventory.add(testKnife);
       expectedInventory.add(testSword);
       expectedInventory.add(testStaff);
       assertEquals(expectedInventory,testController.getInventory());
       testController.equipWeapon(testWhiteMage,testStaff);
       assertEquals(testStaff, testWhiteMage.getEquippedWeapon());
       assertNotEquals(expectedInventory,testController.getInventory());
       expectedInventory.remove(testStaff);
       assertEquals(expectedInventory,testController.getInventory());
   }


    /**
     * This test checks if the actionAttackCharacter method of the controller works properly.
     */
   @Test
   void attackInteractionTest(){
       GameController controller = new GameController(true);
       BlackMage blackMage = controller.createBlackMage("black mage",100,10);
       Staff staff1 = controller.createStaff("staff", 25,20);
       Staff staff2 = new Staff("staff2",25,20);
       Sword sword = controller.createSword("sword",999,1);
       blackMage.setEquippedWeapon(staff2);
       assertFalse(controller.getInventory().contains(staff2));
       controller.equipWeapon(blackMage,staff1);
       assertTrue(controller.getInventory().contains(staff2));
       controller.equipWeapon(blackMage,sword);
       Enemy enemy = controller.createEnemy("enemy",20,100,10,25,"img\\Apocalypse.png");
       int damage1 = controller.actionAttackCharacter(enemy,blackMage);
       assertEquals(15,damage1);
       BlackMage expectedBlackMage = new BlackMage("black mage", 100, 85,10);
       expectedBlackMage.setEquippedWeapon(staff1);
       assertEquals(expectedBlackMage,blackMage);
       int damage2 = controller.actionAttackCharacter(blackMage,enemy);
       assertEquals(15,damage2);
       Enemy expectedEnemy = new Enemy("enemy",20,100,85,10,25,"img\\Apocalypse.png");
       assertEquals(expectedEnemy,enemy);
   }

   @Test
   void onDeathTest(){
       GameController gameController = new GameController(true);
       Enemy enemy1 = gameController.createEnemy("enemy1", 1,1,0,1,"img\\Apocalypse.png");
       Enemy enemy2 = gameController.createEnemy("enemy2", 1,1,0,1,"img\\Apocalypse.png");
       BlackMage blackMage1 = gameController.createBlackMage("blackmage1",1,0);
       BlackMage blackMage2 = gameController.createBlackMage("blackmage2",1,0);
       Staff staff = gameController.createStaff("staff",100,1);
       gameController.equipWeapon(blackMage2,staff);

       BlockingQueue<ICharacter> turnsQueue = gameController.getTurnsQueue();
       turnsQueue.add(enemy1);
       turnsQueue.add(enemy2);
       turnsQueue.add(blackMage1);
       turnsQueue.add(blackMage2);

       gameController.actionAttackCharacter(enemy1,blackMage1);
       assertFalse(gameController.getPlayerCharacters().contains(blackMage1));
       assertFalse(gameController.getTurnsQueue().contains(blackMage1));

       gameController.actionAttackCharacter(blackMage2,enemy1);
       assertFalse(gameController.getEnemies().contains(enemy1));
       assertFalse(gameController.getTurnsQueue().contains(enemy1));
   }

   @Test
    void PhaseTest() throws InterruptedException {
       GameController controller = new GameController(true);
       Enemy enemy = controller.createEnemy("enemy",30,100,10,20,"img\\Apocalypse.png");
       BlackMage blackMage = controller.createBlackMage("blackMage",100,10);
       blackMage.setEquippedWeapon(new Staff("firstStaff",20,10));
       Staff staff = controller.createStaff("secondStaff",20,10);
       controller.setPhase(new StartGamePhase("StartTurnPhase"));
       controller.tryStartTurns();
       Thread.sleep(4000);
       assertEquals(2,controller.getTurnsQueue().size());
       assertEquals("PlayerBeginTurnPhase",controller.getPhase().getPhaseName());
       controller.toEquipWeaponPhase();
       assertEquals("EquipWeaponPhase",controller.getPhase().getPhaseName());
       LinkedList<IWeapon> expectedInventory = new LinkedList<>();
       expectedInventory.add(staff);
       assertEquals(expectedInventory,controller.getInventory());
       controller.tryEquipWeapon(blackMage,staff);
       assertEquals(staff,blackMage.getEquippedWeapon());
       assertFalse(controller.getInventory().isEmpty());
       assertFalse(controller.getInventory().contains(staff));
       controller.toPlayerBeginTurnPhase();
       assertEquals("PlayerBeginTurnPhase",controller.getPhase().getPhaseName());
       controller.toSelectAttackTargetPhase();
       assertEquals("SelectAttackTargetPhase", controller.getPhase().getPhaseName());
       controller.tryActionAttackCharacter(blackMage,enemy);
       assertEquals(90,enemy.getCurrentHealthPoints());
       controller.toEndTurnPhase();
       assertEquals("EndTurnPhase",controller.getPhase().getPhaseName());
       controller.tryEndTurn(blackMage);
       assertEquals(1,controller.getTurnsQueue().size());
       assertEquals("EnemyBeginTurnPhase",controller.getPhase().getPhaseName());
       controller.tryActionAttackRandomPlayerCharacter(enemy);
       assertEquals(90,blackMage.getCurrentHealthPoints());
       controller.toEndTurnPhase();
       controller.tryEndTurn(enemy);
       assertEquals(0,controller.getTurnsQueue().size());
   }

   @Test
    void endGameTest() throws InterruptedException {
       GameController gameController1 = new GameController(true);
       gameController1.setPhase(new StartGamePhase("StartGamePhase"));
       Enemy enemy1 = gameController1.createEnemy("enemy1",30,10,10,10,"img\\Apocalypse.png");
       BlackMage blackMage1 = gameController1.createBlackMage("blackMage1",100,100);
       blackMage1.setEquippedWeapon(new Staff("staff",100,10));
       gameController1.tryStartTurns();
       Thread.sleep(4000);
       gameController1.toSelectAttackTargetPhase();
       assertEquals(0,gameController1.getGameStatus());
       gameController1.actionAttackCharacter(blackMage1,enemy1);
       assertEquals("EndGamePhase",gameController1.getPhase().getPhaseName());
       assertEquals(1,gameController1.getGameStatus());

       GameController gameController2 = new GameController(true);
       gameController2.setPhase(new StartGamePhase("StartGamePhase"));
       Enemy enemy2 = gameController2.createEnemy("enemy2",10,100,100,100,"img\\Apocalypse.png");
       BlackMage blackMage2 = gameController2.createBlackMage("blackMage2",10,10);
       blackMage2.setEquippedWeapon(new Staff("staff",10,30));
       gameController2.tryStartTurns();
       Thread.sleep(4000);
       assertEquals("EnemyBeginTurnPhase",gameController2.getPhase().getPhaseName());
       assertEquals(0,gameController2.getGameStatus());
       gameController2.tryActionAttackRandomPlayerCharacter(enemy2);
       Thread.sleep(1000);
       assertEquals("EndGamePhase",gameController2.getPhase().getPhaseName());
       assertEquals(-1,gameController2.getGameStatus());
   }

   @Test
    void tryMethodsExceptionsTest(){
       EndGamePhase endGamePhase = new EndGamePhase("EndGamePhase");
       EndTurnPhase endTurnPhase = new EndTurnPhase("EndTurnPhase");
       EnemyBeginTurnPhase enemyBeginTurnPhase = new EnemyBeginTurnPhase("EnemyBeginTurnPhase");
       EquipWeaponPhase equipWeaponPhase = new EquipWeaponPhase("EquipWeaponPhase");
       PlayerBeginTurnPhase playerBeginTurnPhase = new PlayerBeginTurnPhase("PlayerBeginTurnPhase");
       SelectAttackTargetPhase selectAttackTargetPhase = new SelectAttackTargetPhase("SelectAttackTargetPhase");
       StartGamePhase startGamePhase = new StartGamePhase("StartGamePhase");
       GameController gameController = new GameController(true);
       Enemy enemy = gameController.createEnemy("enemy",10,100,50,100,"img\\Apocalypse.png");
       BlackMage blackMage = gameController.createBlackMage("blackMage",100,50);
       blackMage.setEquippedWeapon(new Staff("specialStaff",100,10));
       Staff staff = gameController.createStaff("staff",100,10);

       gameController.setPhase(endGamePhase);
       gameController.toPlayerBeginTurnPhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);
       gameController.toEquipWeaponPhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);
       gameController.toSelectAttackTargetPhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);
       gameController.toEnemyBeginTurnPhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);
       gameController.toEndTurnPhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);
       gameController.toEndGamePhase();
       assertTrue(gameController.getPhase() instanceof EndGamePhase);


       gameController.tryStartTurns();
       assertTrue(gameController.getTurnsQueue().isEmpty());
       LinkedList info = gameController.tryActionAttackRandomPlayerCharacter(enemy);
       assertEquals(null,info);
       int inflictedDamage = gameController.tryActionAttackCharacter(blackMage,enemy);
       assertTrue(inflictedDamage==-1);
       int ret = gameController.tryEquipWeapon(blackMage,staff);
       assertEquals(-1,ret);
       ICharacter head = gameController.getTurnsQueue().peek();
       gameController.tryEndTurn(head);
       assertEquals(head,gameController.turnsQueue.peek());

       gameController.setPhase(equipWeaponPhase);
       assertEquals(0,gameController.getGameStatus());
       gameController.tryPlayerWins();
       assertEquals(0,gameController.getGameStatus());
       gameController.tryPlayerLoses();
       assertEquals(0,gameController.getGameStatus());
   }

    @Test
    public void gettersTest(){
       LinkedList<String> expectedPlayerCharactersNames = new LinkedList<String>();
       expectedPlayerCharactersNames.add(testBlackMage.getName());
       expectedPlayerCharactersNames.add(testWhiteMage.getName());
       expectedPlayerCharactersNames.add(testEngineer.getName());
       expectedPlayerCharactersNames.add(testKnight.getName());
       expectedPlayerCharactersNames.add(testThief.getName());
       assertEquals(expectedPlayerCharactersNames,testController.getNamesPlayerCharactersAlive());

       LinkedList<String> expectedEnemiesNames = new LinkedList<String>();
       expectedEnemiesNames.add(testEnemy.getName());
       assertEquals(expectedEnemiesNames,testController.getNamesEnemiesAlive());

       LinkedList<String> expectedInventoryWeaponsNames = new LinkedList<String>();
       expectedInventoryWeaponsNames.add(testAxe.getName());
       expectedInventoryWeaponsNames.add(testBow.getName());
       expectedInventoryWeaponsNames.add(testKnife.getName());
       expectedInventoryWeaponsNames.add(testSword.getName());
       expectedInventoryWeaponsNames.add(testStaff.getName());
       assertEquals(expectedInventoryWeaponsNames,testController.getNameInventoryWeapons());

       LinkedList<Integer> expectedInventoryWeaponsAtk = new LinkedList<>();
       expectedInventoryWeaponsAtk.add(testAxe.getDamage());
       expectedInventoryWeaponsAtk.add(testBow.getDamage());
       expectedInventoryWeaponsAtk.add(testKnife.getDamage());
       expectedInventoryWeaponsAtk.add(testSword.getDamage());
       expectedInventoryWeaponsAtk.add(testStaff.getDamage());
       assertEquals(expectedInventoryWeaponsAtk,testController.getAtkInventoryWeapons());

       LinkedList<Integer> expectedInventoryWeaponsWeight = new LinkedList<>();
       expectedInventoryWeaponsWeight.add(testAxe.getWeight());
       expectedInventoryWeaponsWeight.add(testBow.getWeight());
       expectedInventoryWeaponsWeight.add(testKnife.getWeight());
       expectedInventoryWeaponsWeight.add(testSword.getWeight());
       expectedInventoryWeaponsWeight.add(testStaff.getWeight());
       assertEquals(expectedInventoryWeaponsWeight,testController.getWeightInventoryWeapons());

       LinkedList<Integer> expectedPlayerCharactersHp = new LinkedList<>();
       expectedPlayerCharactersHp.add(testBlackMage.getCurrentHealthPoints());
       expectedPlayerCharactersHp.add(testWhiteMage.getCurrentHealthPoints());
       expectedPlayerCharactersHp.add(testEngineer.getCurrentHealthPoints());
       expectedPlayerCharactersHp.add(testKnight.getCurrentHealthPoints());
       expectedPlayerCharactersHp.add(testThief.getCurrentHealthPoints());
       assertEquals(expectedPlayerCharactersHp,testController.getCurrentHpPlayerCharactersAlive());

       LinkedList<Integer> expectedEnemiesHp = new LinkedList<Integer>();
       expectedEnemiesHp.add(testEnemy.getCurrentHealthPoints());
       assertEquals(expectedEnemiesHp,testController.getCurrentHpEnemiesAlive());

       LinkedList<Integer> expectedPlayerCharactersDef = new LinkedList<>();
       expectedPlayerCharactersDef.add(testBlackMage.getDefensePoints());
       expectedPlayerCharactersDef.add(testWhiteMage.getDefensePoints());
       expectedPlayerCharactersDef.add(testEngineer.getDefensePoints());
       expectedPlayerCharactersDef.add(testKnight.getDefensePoints());
       expectedPlayerCharactersDef.add(testThief.getDefensePoints());
       assertEquals(expectedPlayerCharactersDef,testController.getDefPlayerCharactersAlive());

       LinkedList<Integer> expectedEnemiesDef = new LinkedList<>();
       expectedEnemiesDef.add(testEnemy.getDefensePoints());
       assertEquals(expectedEnemiesDef,testController.getDefEnemiesAlive());

       LinkedList<Integer> expectedEnemiesAtk = new LinkedList<>();
       expectedEnemiesAtk.add(testEnemy.getDamage());
       assertEquals(expectedEnemiesAtk,testController.getAtkEnemiesAlive());

       testBlackMage.setEquippedWeapon(testStaff);
       testKnight.setEquippedWeapon(testAxe);
       LinkedList<Integer> expectedPlayerCharactersAtk = new LinkedList<>();
       expectedPlayerCharactersAtk.add(100);
       expectedPlayerCharactersAtk.add(0);
       expectedPlayerCharactersAtk.add(0);
       expectedPlayerCharactersAtk.add(100);
       expectedPlayerCharactersAtk.add(0);
       assertEquals(expectedPlayerCharactersAtk,testController.getAtkPlayerCharactersAlive());

       LinkedList<String> playerCharacterSkins = new LinkedList<>();
       playerCharacterSkins.add("img\\blackmage.png");
       playerCharacterSkins.add("img\\whitemage.png");
       playerCharacterSkins.add("img\\engineer.png");
       playerCharacterSkins.add("img\\knight.png");
       playerCharacterSkins.add("img\\thief.png");
       assertEquals(playerCharacterSkins,testController.getPlayerCharacterSkins());

       LinkedList<String> enemiesSkins = new LinkedList<>();
       enemiesSkins.add("img\\Apocalypse.png");
       assertEquals(enemiesSkins,testController.getEnemiesSkins());

       LinkedList<String> expectedInventoryWeaponsSkins = new LinkedList<>();
       expectedInventoryWeaponsSkins.add("img\\axe.gif");
       expectedInventoryWeaponsSkins.add("img\\bow.gif");
       expectedInventoryWeaponsSkins.add("img\\knife.gif");
       expectedInventoryWeaponsSkins.add("img\\sword.gif");
       expectedInventoryWeaponsSkins.add("img\\staff.gif");
       assertEquals(expectedInventoryWeaponsSkins,testController.getSkinInventoryWeapons());

       GameController controller = new GameController();
       BlackMage blackMage = controller.createBlackMage("blackMage",100,100);
       controller.getTurnsQueue().add(blackMage);
       assertEquals("",controller.getNameWeaponPlayerInTurn());
       blackMage.setEquippedWeapon(new Staff("staff",100,100));
       assertEquals("staff",controller.getNameWeaponPlayerInTurn());
       assertEquals("blackMage",controller.getNameCharacterInTurn());

       BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
       turnsQueue.add(testBlackMage);
       testController.setTurnsQueue(turnsQueue);
       assertEquals(testBlackMage, testController.getPlayerCharacterInTurn());
   }

   @Test
    public void gameSetUpTest(){
       GameController controller = new GameController(true);
       assertEquals(0,controller.getEnemies().size());
       assertEquals(0,controller.getInventory().size());
       assertEquals(null,controller.getPhase());
       controller.gameSetUp();
       assertEquals(5,controller.getInventory().size());
       assertEquals(new StartGamePhase("StartGamePhase"),controller.getPhase());
   }

   @Test
    public void setViewTest(){
       GameController controller = new GameController(true);
       View view  = new View();
       assertEquals(null,controller.getView());
       controller.setView(view);
       assertSame(view, controller.getView());
   }

   @Test
    public void addToQueueTest(){
       BlackMage blackMage = new BlackMage("blackMage",100,100,100);
       GameController controller = new GameController(true);
       controller.addToQueue(blackMage);
       assertEquals(0,controller.getTurnsQueue().size());
   }



}
