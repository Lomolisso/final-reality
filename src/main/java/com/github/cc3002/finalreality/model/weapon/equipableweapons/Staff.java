package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.WhiteMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Thief;
import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Staff extends AbstractWeapon {
    /**
     * Creates a weapon of type STAFF that has a name, an amount of damage that can inflict and a weight.
     * */
    public Staff(final String name, final int damage, final int weight){
        super(name,damage,weight);
        skin = "img\\staff.gif";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff staff = (Staff) o;
        return getDamage() == staff.getDamage() &&
                getWeight() == staff.getWeight() &&
                getName().equals(staff.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }

    @Override
    public int equipThief(Thief thief){
        thief.setEquippedWeapon(this);
        return 1;
    }

    @Override
    public int equipBlackMage(BlackMage blackmage){
        blackmage.setEquippedWeapon(this);
        return 1;
    }

    @Override
    public int equipWhiteMage(WhiteMage whitemage){
        whitemage.setEquippedWeapon(this);
        return 1;
    }
}
