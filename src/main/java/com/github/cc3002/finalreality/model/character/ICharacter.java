package com.github.cc3002.finalreality.model.character;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public interface ICharacter {
  /**
   * Returns the name of the character.
   */
  String getName();

  /**
   * Returns the max health points of the character.
   */
  int getMaxHealthPoints();

  /**
   * Returns the current health points of the character.
   */
  int getCurrentHealthPoints();

  /**
   * Returns the defense points of the character.
   */
  int getDefensePoints();

  /**
   * sets the current health points of a character.
   */
  void setCurrentHealthPoints(int newCurrentHealthPoints);

  /**
   * returns an int that represents the damage that is going to be
   * inflicted to an ICharacter.
   */
  int decideInflictedDamage(ICharacter target);

  /**
   * Attacks another character and returns the inflicted damage.
   */
  int attackCharacter(ICharacter target);

  /**
   * This method adds a listener to a specific property change support of an ICharacter.
   */
  void addListenerPCSHealth(PropertyChangeListener listener);
  void addListenerPCSTurn(PropertyChangeListener listener);

  /**
   * This method returns the timer of each character.
   */
  ScheduledExecutorService getScheduledExecutor();

  /**
   * This method sets the timer of a character.
   */
  void setScheduledExecutor(ScheduledExecutorService scheduledExecutor);

  /**
   * This method calculates the waitPeriod in order to enter again the Queue.
   */
  long calculateWaitPeriod();

  /**
   * This method notifies a handler of the controller that the character has 0
   * health points.
   */
  void notifyZeroHealth();

  /**
   * This method notifies a handler of the controller that the turn of the character
   * just began.
   */
  void notifyTurn();

  /**
   * This method returns a string with the path of the skin of an ICharacter
   */
  public String getSkin();

  /**
   * This method sets the skin of an ICharacter.
   */
  public void setSkin(String string);
}