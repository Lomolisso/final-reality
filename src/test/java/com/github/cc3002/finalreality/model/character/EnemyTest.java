package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playablecharacters.magic.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EnemyTest extends AbstractCharacterTest {
  protected Enemy testEnemy1;
  protected Enemy testEnemyLowHp;
  protected ICharacter testEnemy2;

  @BeforeEach
  void setUp() {
    testEnemy1 = new Enemy("test_enemy_1",10, 100,100, 25, 10,"img\\Apocalypse.png");
    testEnemy2 = new Enemy("test_enemy_2",10, 100,100, 25, 10,"img\\Apocalypse.png");
    testEnemyLowHp = new Enemy("low_hp",10, 100,10, 0, 50,"img\\Apocalypse.png");

  }

  protected void checkConstruction(Enemy testEnemy1, ICharacter testEnemy2, Enemy expectedEnemy1,
                                   WhiteMage differentClass, Enemy differentWeight, ICharacter differentName,
                                   ICharacter differentCurrentHealth, ICharacter differentMaxHealth,
                                   ICharacter differentDefense, ICharacter differentDamage,ICharacter differentSkin){

    assertEquals(testEnemy1,testEnemy1);
    assertEquals(expectedEnemy1,testEnemy1);
    assertEquals(expectedEnemy1.hashCode(),testEnemy1.hashCode());

    assertNotEquals(testEnemy1,differentClass);
    assertNotEquals(testEnemy1,differentWeight);
    assertNotEquals(testEnemy1,differentDamage);
    assertNotEquals(testEnemy1,differentSkin);

    super.checkConstruction(testEnemy2, differentName,differentCurrentHealth, differentMaxHealth,differentDefense);
  }

  @Test
  void constructorTest() {

    HashMap<Integer,IWeapon> testInventory = new HashMap<Integer, IWeapon>();
    Enemy expectedEnemy = new Enemy("test_enemy_1",10, 100,100, 25, 10,"img\\Apocalypse.png");
    WhiteMage differentClass = new WhiteMage("test_enemy_1",100,100, 25);
    ICharacter differentName = new Enemy("differentName",10, 100,100, 25, 10,"img\\Apocalypse.png");
    Enemy differentSkin = new Enemy("test_enemy_1",10, 100,100, 25, 10,"img\\Latrivan.png");
    Enemy differentWeight = new Enemy("test_enemy_1",11, 100,100, 25, 10,"img\\Apocalypse.png");
    Enemy differentDamage = new Enemy("test_enemy_1",10, 100,100, 25, 11,"img\\Apocalypse.png");
    ICharacter differentCurrentHealth = new Enemy("test_enemy_2",10, 100,99, 25, 10,"img\\Apocalypse.png");
    ICharacter differentMaxHealth = new Enemy("test_enemy_2",10, 101,100, 25, 10,"img\\Apocalypse.png");
    ICharacter differentDefense = new Enemy("test_enemy_2",10, 100,100, 26, 10,"img\\Apocalypse.png");

    this.checkConstruction(testEnemy1, testEnemy2, expectedEnemy,
            differentClass, differentWeight, differentName, differentCurrentHealth,differentMaxHealth, differentDefense, differentDamage,differentSkin);
  }
  @Test
  void getWeightTest(){
    int expectedEnemy1Weight = 10;
    int differentWeight = 11;
    int testEnemy1Weight = testEnemy1.getWeight();
    assertEquals(expectedEnemy1Weight,testEnemy1Weight);
    assertNotEquals(differentWeight,testEnemy1.getWeight());
  }

  @Test
  void attackCharacterTest(){
    Enemy enemy1 = testEnemy1;
    Enemy enemy2 = testEnemyLowHp;
    checkAttackCharacterMethod(enemy1, enemy2);
  }

  @Test
  void calculateWaitPeriodEnemyTest(){
    Enemy enemy = testEnemy1;
    assertEquals(1,testEnemy1.calculateWaitPeriod());
  }
}