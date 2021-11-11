package com.github.cc3002.finalreality.model.character;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import javafx.beans.Observable;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.ImageView;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Raul de la Fuente..
 */
public abstract class AbstractCharacter implements ICharacter{

  private final String name;
  protected ScheduledExecutorService scheduledExecutor;
  private int maxHealthPoints;
  private int currentHealthPoints;
  private int defensePoints;
  protected String skin;
  private final PropertyChangeSupport pcsHealth = new PropertyChangeSupport(this);
  private final PropertyChangeSupport pcsTurn = new PropertyChangeSupport(this);


  /**
   * Creates a character with a name and the queue with the characters ready to play.
   */
  protected AbstractCharacter(@NotNull String name, int maxHealthPoints, int currentHealthPoints, int defensePoints) {
    this.name = name;
    this.maxHealthPoints = maxHealthPoints;
    this.currentHealthPoints = currentHealthPoints;
    this.defensePoints = defensePoints;
  }
  @Override
  public void notifyZeroHealth(){pcsHealth.firePropertyChange("zero health",null,this); }

  @Override
  public void notifyTurn(){pcsTurn.firePropertyChange("starts turn",null,this);}

  @Override
  public void addListenerPCSHealth(PropertyChangeListener listener){
    pcsHealth.addPropertyChangeListener(listener);
  }

  @Override
  public void addListenerPCSTurn(PropertyChangeListener listener){
    pcsTurn.addPropertyChangeListener(listener);
  }

  @Override
  public ScheduledExecutorService getScheduledExecutor(){
    return scheduledExecutor;
  }

  @Override
  public int attackCharacter(ICharacter target){
    if (this.getCurrentHealthPoints() == 0){
      return 0;
    }
    int inflictedDamage = this.decideInflictedDamage(target);
    int previousTargetHealth = target.getCurrentHealthPoints();
    int newTargetHealth = previousTargetHealth - inflictedDamage;
    if (newTargetHealth <= 0){
      newTargetHealth = 0;
      target.notifyZeroHealth();
    }
    target.setCurrentHealthPoints(newTargetHealth);
    return inflictedDamage;
  }
  @Override
  public String getSkin() {
    return skin;
  }
  @Override
  public void setSkin(String skin) {
    this.skin = skin;
  }

  @Override
  public void setScheduledExecutor(ScheduledExecutorService scheduledExecutor) {
    this.scheduledExecutor = scheduledExecutor;
  }

  @Override
  public abstract long calculateWaitPeriod();

  @Override
  abstract public int decideInflictedDamage(ICharacter target);

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }

  @Override
  public int getCurrentHealthPoints() {
    return currentHealthPoints;
  }

  @Override
  public void setCurrentHealthPoints(int newCurrentHealthPoints) {
    this.currentHealthPoints = newCurrentHealthPoints;
  }

  @Override
  public int getDefensePoints() {
    return defensePoints;
  }
}