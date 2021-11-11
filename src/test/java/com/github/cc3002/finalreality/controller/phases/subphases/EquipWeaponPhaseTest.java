package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class EquipWeaponPhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController();
        originalPhase = new EquipWeaponPhase("EquipWeaponPhase");
        testPhase = new EquipWeaponPhase("EquipWeaponPhase");
        testController.setPhase(testPhase);
        entitySetUp();
    }

    @Override
    public void checkToEquipWeaponPhase() {
        try {
            testPhase.toEquipWeaponPhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new EquipWeaponPhase("EquipWeaponPhase"),testController.getPhase());
        }
    }

    @Override
    public void checkToPlayerBeginTurnPhase() {
        try {
            testPhase.toPlayerBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"),testController.getPhase());
    }

    @Override
    public void checkEquipWeapon() {
        IWeapon previousWeapon = testCharacter3.getEquippedWeapon();
        assertFalse(testController.getInventory().contains(previousWeapon));
        try {
            testPhase.equipWeapon(testCharacter3,testWeapon);
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        assertTrue(testController.getInventory().contains(previousWeapon));
        assertEquals(testWeapon,testCharacter3.getEquippedWeapon());
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        EquipWeaponPhase expectedPhase = new EquipWeaponPhase("EquipWeaponPhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));
        checkToEndTurnPhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));
        checkToEndGamePhase();
        testController.setPhase(new EquipWeaponPhase("EquipWeaponPhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
