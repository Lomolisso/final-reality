package com.github.cc3002.finalreality.model.character;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author <Your name>
 */
public class Enemy extends AbstractCharacter {
  private final int weight;
  private final int damage;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int weight, int maxHealthPoints, int currentHealthPoints, int defensePoints, int damage, String skin) {
    super(name, maxHealthPoints, currentHealthPoints, defensePoints);
    this.weight = weight;
    this.damage = damage;
    this.skin = skin;
  }

  @Override
  public int decideInflictedDamage(ICharacter target){
    int targetDefensePoints = target.getDefensePoints();
    if(targetDefensePoints>=this.damage){return 0;}
    return (this.damage - targetDefensePoints);
  }
  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the damage of this enemy.
   */
  public int getDamage() {
    return damage;
  }


  @Override
  public long calculateWaitPeriod(){
    return this.getWeight()/10;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getName().equals(enemy.getName())
            && getWeight() == enemy.getWeight()
            && getCurrentHealthPoints() == enemy.getCurrentHealthPoints()
            && getMaxHealthPoints() == enemy.getMaxHealthPoints()
            && getDefensePoints() == enemy.getDefensePoints()
            && getDamage() == enemy.getDamage()
            && getSkin().equals(enemy.getSkin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getWeight(), getMaxHealthPoints(), getCurrentHealthPoints(), getDefensePoints(), getDamage(),getSkin());
  }
}