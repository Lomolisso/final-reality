package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerBeginTurnPhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController();
        originalPhase = new PlayerBeginTurnPhase("PlayerBeginTurnPhase");
        testPhase = new PlayerBeginTurnPhase("PlayerBeginTurnPhase");
        testController.setPhase(testPhase);
        entitySetUp();
    }

    @Override
    public void checkToPlayerBeginTurnPhase() {
        try {
            testPhase.toPlayerBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"),testController.getPhase());
        }
    }

    @Override
    public void checkToEquipWeaponPhase() {
        try {
            testPhase.toEquipWeaponPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new EquipWeaponPhase("EquipWeaponPhase"),testController.getPhase());
    }

    @Override
    public void checkToSelectAttackTargetPhase() {
        try {
            testPhase.toSelectAttackTargetPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new SelectAttackTargetPhase("SelectAttackTargetPhase"),testController.getPhase());
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        PlayerBeginTurnPhase expectedPhase = new PlayerBeginTurnPhase("PlayerBeginTurnPhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
        checkToEndTurnPhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
        checkToEndGamePhase();
        testController.setPhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
