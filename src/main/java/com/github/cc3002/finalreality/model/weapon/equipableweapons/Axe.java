package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Knight;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Engineer;
import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Axe extends AbstractWeapon {
    /**
     * Creates a weapon of type AXE that has a name, an amount of damage that can inflict and a weight.
     * */
    public Axe(final String name, final int damage, final int weight){
        super(name,damage,weight);
        skin = "img\\axe.gif";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe axe = (Axe) o;
        return getDamage() == axe.getDamage() &&
                getWeight() == axe.getWeight() &&
                getName().equals(axe.getName());
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
    public int equipEngineer(Engineer engineer){
        engineer.setEquippedWeapon(this);
        return 1;
    }
}
