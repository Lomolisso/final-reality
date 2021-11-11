package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.LinkedList;
import java.util.Objects;

public class PlayerBeginTurnPhase extends Phase {
    public PlayerBeginTurnPhase(String phaseName) {
        super(phaseName);
    }

    @Override
    public void toEquipWeaponPhase() throws InvalidTransitionException {
        changePhase(new EquipWeaponPhase("EquipWeaponPhase"));
    }

    @Override
    public void toSelectAttackTargetPhase() throws InvalidTransitionException {
        changePhase(new SelectAttackTargetPhase("SelectAttackTargetPhase"));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof PlayerBeginTurnPhase)){
            return false;
        }
        final PlayerBeginTurnPhase that = (PlayerBeginTurnPhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}
