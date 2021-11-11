package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Knight;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Thief;
import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Sword extends AbstractWeapon {
    /**
     * Creates a weapon of type SWORD that has a name, an amount of damage that can inflict and a weight.
     * */
    public Sword(final String name, final int damage, final int weight){
        super(name,damage,weight);
        skin = "img\\sword.gif";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sword)) {
            return false;
        }
        final Sword sword = (Sword) o;
        return getDamage() == sword.getDamage() &&
                getWeight() == sword.getWeight() &&
                getName().equals(sword.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }

    @Override
    public int equipKnight(Knight knight){
        knight.setEquippedWeapon(this);
        return 1;
    }

    @Override
    public int equipThief(Thief thief){
        thief.setEquippedWeapon(this);
        return 1;
    }
}
