package com.github.cc3002.finalreality.controller.phases;


import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.util.LinkedList;
import java.util.Objects;

/**
 * This class holds all the common behaviour of a phase of the game, it is an application of
 * the state pattern.
 */
public class Phase {
    protected String phaseName;
    protected GameController controller;
    protected boolean playerWins = false;
    protected boolean playerLost = false;

    public Phase(String phaseName) {
        this.phaseName = phaseName;
    }

    public void setController(GameController gameController){this.controller = gameController;}
    public void changePhase(Phase phase){controller.setPhase(phase);}

    public void toPlayerBeginTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to player begin turn phase is not allowed!.");
    }
    public void toEnemyBeginTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to enemy begin turn phase is not allowed!.");
    }
    public void toEquipWeaponPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to equip weapon phase is not allowed!.");
    }
    public void toSelectAttackTargetPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to select attack target phase is not allowed!.");
    }
    public void toEndTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to end turn phase is not allowed!.");
    }
    public void toEndGamePhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Transitioning to end game phase is not allowed!.");
    }
    public void startTurns() throws InvalidMethodCalledException{
        throw new InvalidMethodCalledException("Calling method startTurns is not allowed in" + phaseName);
    }
    public int actionAttackCharacter(ICharacter attacker, ICharacter Target) throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method actionAttackCharacter is not allowed in" + phaseName);
    }

    public LinkedList actionAttackRandomPlayerCharacter(ICharacter target) throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method actionAttackRandomPlayerCharacter is not allowed in" + phaseName);
    }

    public int equipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon) throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method equipWeapon is not allowed in" + phaseName);
    }
    public void endTurn(ICharacter character) throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method endTurn is not allowed in" + phaseName);
    }
    public void playerWins() throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method playerWins is not allowed in" + phaseName);
    }
    public void playerLoses() throws InvalidMethodCalledException {
        throw new InvalidMethodCalledException("Calling method playerLost is not allowed in" + phaseName);
    }

    public String getPhaseName(){
        return phaseName;
    }


}