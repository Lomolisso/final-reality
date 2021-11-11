package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;

import java.util.Objects;

public class StartGamePhase extends Phase {

    public StartGamePhase(String phaseName) {
        super(phaseName);
    }

    @Override
    public void toPlayerBeginTurnPhase() throws InvalidTransitionException {
        changePhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
    }

    @Override
    public void toEnemyBeginTurnPhase() throws InvalidTransitionException {
        changePhase(new EnemyBeginTurnPhase("EnemyBeginTurnPhase"));
    }

    @Override
    public void startTurns() throws InvalidMethodCalledException {
        controller.startTurns();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof StartGamePhase)){
            return false;
        }
        final StartGamePhase that = (StartGamePhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}