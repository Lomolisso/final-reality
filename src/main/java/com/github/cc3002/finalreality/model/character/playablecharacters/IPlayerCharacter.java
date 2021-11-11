package com.github.cc3002.finalreality.model.character.playablecharacters;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter {

    /**
     * If it is possible, it equips a weapon into a playable character and
     * returns 1, if it is not, it only returns -1.
     */
    int equipWeapon(IWeapon weapon);

    /**
     * Returns the equipped weapon of the PlayerCharacter.
     */
    IWeapon getEquippedWeapon();

    /**
     * sets an IWeapon as the equippedWeapon of an IPlayerCharacter.
     */
    void setEquippedWeapon(IWeapon weapon);

}
