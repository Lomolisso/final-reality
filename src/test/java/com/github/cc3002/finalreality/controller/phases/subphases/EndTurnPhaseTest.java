package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class EndTurnPhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController(true);
        originalPhase = new EndTurnPhase("EndTurnPhase");
        testPhase = new EndTurnPhase("EndTurnPhase");
        testController.setPhase(testPhase);
        entitySetUp();
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
    public void checkToPlayerBeginTurnPhase() {
        try {
            testPhase.toPlayerBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"),testController.getPhase());
    }

    @Override
    public void checkToEnemyBeginTurnPhase() {
        try {
            testPhase.toEnemyBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"),testController.getPhase());
    }

    @Override
    public void checkEndTurn() {
        BlockingQueue turnsQueue = testController.getTurnsQueue();
        turnsQueue.add(testCharacter1);
        turnsQueue.add(testCharacter2);
        turnsQueue.add(testCharacter3);
        ICharacter head = testController.getTurnsQueue().peek();
        try {
            testPhase.endTurn(head);
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        assertNotEquals(turnsQueue.peek(), head);
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        EndTurnPhase expectedPhase = new EndTurnPhase("EndTurnPhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));
        checkToEndTurnPhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));
        checkToEndGamePhase();
        testController.setPhase(new EndTurnPhase("EndTurnPhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }

}
