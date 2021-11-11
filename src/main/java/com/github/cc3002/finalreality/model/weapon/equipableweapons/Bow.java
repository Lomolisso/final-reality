package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Thief;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Engineer;
import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Bow extends AbstractWeapon {
    /**
     * Creates a weapon of type BOW that has a name, an amount of damage that can inflict and a weight.
     * */
    public Bow(final String name, final int damage, final int weight){
        super(name,damage,weight);
        skin = "img\\bow.gif";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow bow = (Bow) o;
        return getDamage() == bow.getDamage() &&
                getWeight() == bow.getWeight() &&
                getName().equals(bow.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }

    @Override
    public int equipEngineer(Engineer engineer){
        engineer.setEquippedWeapon(this);
        return 1;
    }

    @Override
    public int equipThief(Thief thief){
        thief.setEquippedWeapon(this);
        return 1;
    }
}
