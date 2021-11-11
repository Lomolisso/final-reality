package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StartGamePhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController(true);
        originalPhase = new StartGamePhase("StartGamePhase");
        testPhase = new StartGamePhase("StartGamePhase");
        testController.setPhase(testPhase);
        entitySetUp();
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
    public void checkStartTurns() throws InterruptedException {
        BlockingQueue expectedTurnsQueue = new LinkedBlockingQueue();
        expectedTurnsQueue.add(testCharacter3);
        expectedTurnsQueue.add(testCharacter1);
        expectedTurnsQueue.add(testCharacter2);
        try {
            testPhase.startTurns();
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
        BlockingQueue turnsQueue = testController.getTurnsQueue();
        assertEquals(testCharacter3,turnsQueue.peek());
        turnsQueue.remove(testCharacter3);
        assertEquals(testCharacter1,turnsQueue.peek());
        turnsQueue.remove(testCharacter1);
        assertEquals(testCharacter2,turnsQueue.peek());

    }

    @Test
    public void constructionTest(){
        StartGamePhase expectedPhase = new StartGamePhase("StartGamePhase");
        EndGamePhase differentPhase = new EndGamePhase("EndGamePhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));
        checkToEndTurnPhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));
        checkToEndGamePhase();
        testController.setPhase(new StartGamePhase("StartGamePhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
