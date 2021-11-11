package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.subphases.*;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PhaseTest {
    protected GameController testController;
    protected Phase originalPhase;
    protected Phase testPhase;
    protected ICharacter testCharacter1;
    protected ICharacter testCharacter2;
    protected IPlayerCharacter testCharacter3;
    protected IWeapon testWeapon;

    public void entitySetUp(){
        testCharacter1 = testController.createEnemy("testCharacter1",30,100,10,50,"img\\Apocalypse.png");
        testCharacter2 = testController.createEnemy("testCharacter2",40,100,10,50,"img\\Apocalypse.png");
        testCharacter3 = testController.createBlackMage("testCharacter3",100,10);
        testCharacter3.setEquippedWeapon(new Staff("specialStaff",100,10));
        testWeapon = testController.createStaff("testWeapon",50,10);
    }

    public void checkToPlayerBeginTurnPhase(){
        try {
            testPhase.toPlayerBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"),testPhase);
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkToEnemyBeginTurnPhase(){
        try {
            testPhase.toEnemyBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"),testController.getPhase());
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkToEquipWeaponPhase(){
        try {
            testPhase.toEquipWeaponPhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new EquipWeaponPhase("EquipWeaponPhase"),testController.getPhase());
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkToSelectAttackTargetPhase(){
        try {
            testPhase.toSelectAttackTargetPhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new SelectAttackTargetPhase("SelectAttackTargetPhase"),testController.getPhase());
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkToEndTurnPhase(){
        try {
            testPhase.toEndTurnPhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new EndTurnPhase("EndTurnPhase"),testController.getPhase());
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkToEndGamePhase(){
        try {
            testPhase.toEndGamePhase();
        } catch (InvalidTransitionException e) {
            assertNotEquals(new EndGamePhase("EndGamePhase"),testController.getPhase());
            assertEquals(originalPhase,testPhase);
            assertEquals(originalPhase.hashCode(),testPhase.hashCode());
        }
    }
    public void checkStartTurns() throws InterruptedException {
        try {
            testPhase.startTurns();
        } catch (InvalidMethodCalledException e) {
            Thread.sleep(5000);
            assertTrue(testController.getTurnsQueue().isEmpty());
        }
    }
    public void checkActionAttackCharacter(){
        int testCharacter2PrevHP = testCharacter2.getCurrentHealthPoints();
        try {
            testPhase.actionAttackCharacter(testCharacter1,testCharacter2);
        } catch (InvalidMethodCalledException e) {
            assertFalse(testCharacter2.getCurrentHealthPoints()<testCharacter2PrevHP);
        }
    }
    public void checkActionAttackRandomPlayerCharacter(){
        LinkedList info = null;
        try {
            info = testPhase.actionAttackRandomPlayerCharacter(testCharacter1);
        } catch (InvalidMethodCalledException e) {
            assertEquals(null,info);
        }
    }
    public void checkEquipWeapon(){
        IWeapon prevWeapon = testCharacter3.getEquippedWeapon();
        try {
            testPhase.equipWeapon(testCharacter3,testWeapon);
        } catch (InvalidMethodCalledException e) {
            assertEquals(testCharacter3.getEquippedWeapon(),prevWeapon);
            assertEquals(testCharacter3.getEquippedWeapon().hashCode(),prevWeapon.hashCode());
        }
    }
    public void checkEndTurn(){
        BlockingQueue turnsQueue = new LinkedBlockingQueue();
        turnsQueue.add(testCharacter1);
        turnsQueue.add(testCharacter2);
        turnsQueue.add(testCharacter3);
        testController.setTurnsQueue(turnsQueue);
        try {
            testPhase.endTurn(testCharacter1);
        } catch (InvalidMethodCalledException e) {
            ICharacter head = testController.getTurnsQueue().peek();
            assertEquals(testCharacter1,head);
        }
    }
    public void checkPlayerWins(){
        try {
            testPhase.playerWins();
        } catch (InvalidMethodCalledException e) {
            assertEquals(0,testController.getGameStatus());
        }
    }
    public void checkPlayerLost(){
        try {
            testPhase.playerLoses();
        } catch (InvalidMethodCalledException e) {
            assertEquals(0,testController.getGameStatus());
        }
    }
}




