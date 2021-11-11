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

public class EnemyBeginTurnPhaseTest extends PhaseTest {
    @BeforeEach
    void setUp(){
        testController = new GameController();
        originalPhase = new EnemyBeginTurnPhase("EnemyBeginTurnPhase");
        testPhase = new EnemyBeginTurnPhase("EnemyBeginTurnPhase");
        testController.setPhase(testPhase);
        entitySetUp();
    }

    @Override
    public void checkToEnemyBeginTurnPhase() {
        try {
            testPhase.toEnemyBeginTurnPhase();
        } catch (InvalidTransitionException e) {
            assertEquals(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"),testController.getPhase());
        }
    }

    @Override
    public void checkToEndTurnPhase() {
        try {
            testPhase.toEndTurnPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        assertEquals(new EndTurnPhase("EndTurnPhase"),testController.getPhase());
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
    public void checkActionAttackRandomPlayerCharacter() {
        try {
            ICharacter cast = (ICharacter) testCharacter3;
            int prevHp = cast.getCurrentHealthPoints();
            System.out.println(prevHp);
            LinkedList info = testPhase.actionAttackRandomPlayerCharacter(testCharacter1);
            assertEquals(cast.getName(),info.get(1));
            assertEquals(cast.getCurrentHealthPoints(),prevHp - (int) info.get(0));

        } catch (InvalidMethodCalledException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void constructionTest(){
        StartGamePhase differentPhase = new StartGamePhase("StartGamePhase");
        EnemyBeginTurnPhase expectedPhase = new EnemyBeginTurnPhase("EnemyBeginTurnPhase");
        assertEquals(testPhase,testPhase);
        assertFalse(testPhase.equals(differentPhase));
        assertEquals(expectedPhase,testPhase);
        assertEquals(expectedPhase.hashCode(),testPhase.hashCode());
    }

    @Test
    public void methodsTest() throws InterruptedException {
        checkToPlayerBeginTurnPhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
        checkToEnemyBeginTurnPhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
        checkToEquipWeaponPhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
        checkToSelectAttackTargetPhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
        checkToEndTurnPhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
        checkToEndGamePhase();
        testController.setPhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));

        checkStartTurns();
        checkActionAttackCharacter();
        checkActionAttackRandomPlayerCharacter();
        checkEquipWeapon();
        checkEndTurn();
        checkPlayerWins();
        checkPlayerLost();
    }
}
