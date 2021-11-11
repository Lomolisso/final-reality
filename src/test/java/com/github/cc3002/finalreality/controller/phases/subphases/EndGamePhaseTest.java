package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.PhaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EndGamePhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController();
        originalPhase = new EndGamePhase("EndGamePhase");
        testPhase = new EndGamePhase("EndGamePhase");
        testController.setPhase(testPhase);
        entitySetUp();
    }


    @Override
    public void checkPlayerWins(){
        try {
            testPhase.playerWins();
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        assertEquals(1,testController.getGameStatus());
    }

    @Override
    public void checkPlayerLost()  {
        try {
            testPhase.playerLoses();
        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
        assertEquals(-1,testController.getGameStatus());
    }

    @Override
    public void checkToEndGamePhase() {
        try {
            testPhase.toEndGamePhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new EndGamePhase("EndGamePhase"),testController.getPhase());
        }
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        EndGamePhase expectedPhase = new EndGamePhase("EndGamePhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkToEndTurnPhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkToEndGamePhase();
        testController.setPhase(new EndGamePhase("EndGamePhase"));
        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
