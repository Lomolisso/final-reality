package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.WhiteMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Knight;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Thief;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Engineer;

public interface IWeapon {

    /**
     * Returns the name of the weapon.
     */
    String getName();

    /**
     * Returns the damage of the weapon.
     */
    int getDamage();

    /**
     * Returns the weight of the weapon.
     */
    int getWeight();
    /**
     * Equips itself to a Knight.
     */
    int equipKnight(Knight knight);

    /**
     * Equips itself to a BlackMage.
     */
    int equipEngineer(Engineer engineer);

    /**
     * Equips itself to a Thief.
     */
    int equipThief(Thief thief);

    /**
     * Equips itself to a Black Mage.
     */
    int equipBlackMage(BlackMage blackmage);

    /**
     * Equips itself to a White Mage.
     */
    int equipWhiteMage(WhiteMage whitemage);

    /**
     * This method returns the skin of a weapon.
     */
    String getSkin();
}




