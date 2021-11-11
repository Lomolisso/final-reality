package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.WhiteMage;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Knight;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Thief;
import com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic.Engineer;

/**
 * A class that holds all the information about the behaviour of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;
  protected String skin;

  /**
   * Creates a weapon with a name, a base damage and a weight.
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }


  @Override
  public String getSkin(){
    return skin;
  }
  @Override
  public String getName() {
    return name;
  }
  @Override
  public int getDamage() {
    return damage;
  }
  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int equipKnight(Knight knight){return -1;}
  @Override
  public int equipEngineer(Engineer engineer){return -1;}
  @Override
  public int equipThief(Thief thief){return -1;}
  @Override
  public int equipBlackMage(BlackMage blackmage){return -1;}
  @Override
  public int equipWhiteMage(WhiteMage whitemage){return -1;}
}