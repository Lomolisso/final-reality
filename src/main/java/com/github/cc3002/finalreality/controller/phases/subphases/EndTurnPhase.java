package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.Objects;

public class EndTurnPhase extends Phase {

    public EndTurnPhase(String phaseName) {
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
    public void endTurn(ICharacter character) throws InvalidMethodCalledException {
        controller.endTurn(character);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof EndTurnPhase)){
            return false;
        }
        final EndTurnPhase that = (EndTurnPhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}
