package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class SelectAttackTargetPhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController();
        originalPhase = new SelectAttackTargetPhase("SelectAttackTargetPhase");
        testPhase = new SelectAttackTargetPhase("SelectAttackTargetPhase");
        testController.setPhase(testPhase);
        entitySetUp();
    }

    @Override
    public void checkToSelectAttackTargetPhase() {
        try {
            testPhase.toSelectAttackTargetPhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new SelectAttackTargetPhase("SelectAttackTargetPhase"),testController.getPhase());
        }
    }

    @Override
    public void checkToEndTurnPhase() {
        try {
            testPhase.toEndGamePhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new EndTurnPhase("EndTurnPhase"),testController.getPhase());
        }
    }

    @Override
    public void checkToEndGamePhase() {
        try {
            testPhase.toEndGamePhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new EndGamePhase("EndGamePhase"),testController.getPhase());
    }

    @Override
    public void checkActionAttackCharacter() {
        int prevHPTestChar1 = testCharacter1.getCurrentHealthPoints();
        try {
            testPhase.actionAttackCharacter((ICharacter) testCharacter3,testCharacter1);
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        assertTrue(testCharacter1.getCurrentHealthPoints()<prevHPTestChar1);
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        SelectAttackTargetPhase expectedPhase = new SelectAttackTargetPhase("SelectAttackTargetPhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
        checkToEndTurnPhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
        checkToEndGamePhase();
        testController.setPhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
