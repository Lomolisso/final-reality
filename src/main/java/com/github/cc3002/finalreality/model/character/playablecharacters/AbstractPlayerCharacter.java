package com.github.cc3002.finalreality.model.character.playablecharacters;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the common behaviour of a single playable character.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter{
  private IWeapon equippedWeapon;

  /**
   * Creates a playable character with a name, a default weapon (null) and the queue with the characters ready to
   * play.
   */
  public AbstractPlayerCharacter(@NotNull String name, int maxHealthPoints, int currentHealthPoints, int defensePoints) {
    super(name, maxHealthPoints, currentHealthPoints, defensePoints);
    this.equippedWeapon = null;
  }

  @Override
  public long calculateWaitPeriod(){
    Random rand = new Random();
    int minWaitTime = rand.nextInt(10);
    IWeapon equippedWeapon = this.equippedWeapon;
    if(equippedWeapon!=null) {
      return this.equippedWeapon.getWeight() / 10;
    }
    return minWaitTime;
  }

  @Override
  public int decideInflictedDamage(ICharacter target){
    int targetDefensePoints = target.getDefensePoints();
    IWeapon weapon = this.getEquippedWeapon();
    if (equippedWeapon==null){return 0;}
    if(targetDefensePoints>=equippedWeapon.getDamage()){return 0;}
    return weapon.getDamage() - targetDefensePoints;
  }

  @Override
  public abstract int equipWeapon(IWeapon weapon);

  @Override
  public IWeapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  @Override
  public void setEquippedWeapon(IWeapon weapon){
    this.equippedWeapon = weapon;
  }
}