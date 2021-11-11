package com.github.cc3002.finalreality.model.character.playablecharacters.magic;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
/**
 * A class that holds all the common behaviour of a single playable character of the mage class.
 *
 * @author <Your name>
 */
public abstract class AbstractMage extends AbstractPlayerCharacter {
    public AbstractMage(@NotNull String name, int maxHealthPoints, int currentHealthPoints, int defensePoints){
        super(name, maxHealthPoints, currentHealthPoints, defensePoints);
    }

}
