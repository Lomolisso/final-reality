package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {
  protected ICharacter testICharacter;


  protected void checkConstruction(final ICharacter testCharacter, final ICharacter differentName,
                                   final ICharacter differentMaxHealth, final ICharacter differentCurrentHealth,
                                   final ICharacter differentDefense) {

    assertNotEquals(testCharacter, differentName);
    assertNotEquals(testCharacter, differentMaxHealth);
    assertNotEquals(testCharacter, differentCurrentHealth);
    assertNotEquals(testCharacter, differentDefense);
  }

  protected void checkAttackCharacterMethod(ICharacter testCharacter, ICharacter lowHpCharacter){
    lowHpCharacter.attackCharacter(testCharacter);
    assertEquals( 75, testCharacter.getCurrentHealthPoints());
    testCharacter.attackCharacter(lowHpCharacter);
    assertEquals(0, lowHpCharacter.getCurrentHealthPoints());
    lowHpCharacter.attackCharacter(testCharacter);
    assertEquals( 75, testCharacter.getCurrentHealthPoints());
  }

  @Test
  public void setSkin(){
    BlackMage blackMage = new BlackMage("black mage",1,1,1);
    String prevSkin = blackMage.getSkin();
    blackMage.setSkin("img\\Apocalypse.png");
    assertEquals("img\\Apocalypse.png",blackMage.getSkin());
  }

  @Test
  public void decideInflictedDamageTest(){
    BlackMage blackMage = new BlackMage("blackMage",100,100,100);
    Enemy enemy1 = new Enemy("enemy1",100,100,100,100,10,"img\\Apocalypse");
    assertEquals(0,blackMage.decideInflictedDamage(enemy1));
    blackMage.setEquippedWeapon(new Staff("staff",10,10));
    assertEquals(0,blackMage.decideInflictedDamage(enemy1));
    assertEquals(0,enemy1.decideInflictedDamage(blackMage));

  }
  @Test
  public void calculateWaitPeriod(){
    BlackMage blackMage = new BlackMage("blackMage",100,100,100);
    assertNotEquals(null,blackMage.calculateWaitPeriod());
  }
}