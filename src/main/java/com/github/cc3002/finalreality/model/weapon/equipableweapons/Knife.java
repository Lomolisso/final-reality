package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Knight;
import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Knife extends AbstractWeapon {
    /**
     * Creates a weapon of type KNIFE that has a name, an amount of damage that can inflict and a weight.
     * */
    public Knife(final String name, final int damage, final int weight){
        super(name,damage,weight);
        skin = "img\\knife.gif";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife knife = (Knife) o;
        return getDamage() == knife.getDamage() &&
                getWeight() == knife.getWeight() &&
                getName().equals(knife.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }

    @Override
    public int equipKnight(Knight knight) {
        knight.setEquippedWeapon(this);
        return 1;
    }

    @Override
    public int equipBlackMage(BlackMage blackmage){
        blackmage.setEquippedWeapon(this);
        return 1;
    }
}
