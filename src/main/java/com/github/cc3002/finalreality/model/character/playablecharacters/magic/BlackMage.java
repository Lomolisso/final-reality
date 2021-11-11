package com.github.cc3002.finalreality.model.character.playablecharacters.magic;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class BlackMage extends AbstractMage {
    /**
    * Creates a new character of the BLACK_MAGE class.
    *
    * @param name
    *     the character's name
    */

    public BlackMage(@NotNull String name, int maxHealthPoints, int currentHealthPoints, int defensePoints) {
        super(name, maxHealthPoints, currentHealthPoints, defensePoints);
        skin = "img\\blackmage.png";
    }

    @Override
    public int equipWeapon(IWeapon weapon){
        int actionId = weapon.equipBlackMage(this);
        return actionId;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof BlackMage)){
            return false;
        }
        final BlackMage that = (BlackMage) o;
        return getEquippedWeapon() == that.getEquippedWeapon()
                && getName().equals(that.getName())
                && getCurrentHealthPoints() == that.getCurrentHealthPoints()
                && getMaxHealthPoints() == that.getMaxHealthPoints()
                && getDefensePoints() == that.getDefensePoints();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),getEquippedWeapon(), getMaxHealthPoints(), getCurrentHealthPoints(), getDefensePoints());
    }
}
