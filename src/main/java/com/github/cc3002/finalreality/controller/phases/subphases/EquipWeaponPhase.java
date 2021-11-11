package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.InvalidTransitionException;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.util.LinkedList;
import java.util.Objects;

public class EquipWeaponPhase extends Phase {
    public EquipWeaponPhase(String phaseName) {
        super(phaseName);
    }

    @Override
    public void toPlayerBeginTurnPhase() throws InvalidTransitionException {
        changePhase(new PlayerBeginTurnPhase("PlayerBeginTurnPhase"));
    }


    @Override
    public int equipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon) throws InvalidMethodCalledException {
        return controller.equipWeapon(playerCharacter,weapon);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof EquipWeaponPhase)){
            return false;
        }
        final EquipWeaponPhase that = (EquipWeaponPhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}
