package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.LinkedList;
import java.util.Objects;

public class SelectAttackTargetPhase extends Phase {
    public SelectAttackTargetPhase(String phaseName) {
        super(phaseName);
    }

    @Override
    public void toEndTurnPhase() throws InvalidTransitionException {
        changePhase(new EndTurnPhase("EndTurnPhase"));
    }

    @Override
    public void toEndGamePhase() throws InvalidTransitionException {
        changePhase(new EndGamePhase("EndGamePhase"));
    }

    @Override
    public int actionAttackCharacter(ICharacter attacker, ICharacter Target) throws InvalidMethodCalledException {
        return controller.actionAttackCharacter(attacker, Target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof SelectAttackTargetPhase)){
            return false;
        }
        final SelectAttackTargetPhase that = (SelectAttackTargetPhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}
