package com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.AbstractPlayerCharacterTest;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ThiefTest extends AbstractPlayerCharacterTest {
    protected Thief testThief1;
    protected Thief testThiefLowHp;


    @BeforeEach
    void setUp(){
        super.basicSetUp();

        testThief1 = new Thief("thief_1",100,100, 25);
        testThief1.setEquippedWeapon(testWeapons.get(3));
        testThiefLowHp = new Thief("low_hp",100,15, 0);
        testThiefLowHp.setEquippedWeapon(testWeapons.get(3));
        testIPlayerCharacter = new Thief("thief_2",100,100, 25);
        testIPlayerCharacter.setEquippedWeapon(testWeapons.get(3));
        testICharacter = new Thief("thief_3",100,100, 25);
    }


    protected void checkConstruction(Thief testThief1, IPlayerCharacter testThief2,
                                     ICharacter testThief3, Thief expectedThief1,
                                     WhiteMage differentClass, IPlayerCharacter differentWeapon,
                                     ICharacter differentName,  ICharacter differentCurrentHealth,
                                     ICharacter differentMaxHealth, ICharacter differentDefense){

        assertEquals(testThief1,testThief1);
        assertEquals(expectedThief1,testThief1);
        assertNotEquals(testThief1,differentClass);
        assertEquals(expectedThief1.hashCode(),testThief1.hashCode());
        super.checkConstruction(testThief2, testThief3, differentWeapon, differentName, differentMaxHealth, differentCurrentHealth, differentDefense);
    }

    @Test
    void constructorTest(){
        var enemy = new Enemy("enemy",10,100,100, 25, 10,"img\\Apocalypse.png");
        var expectedThief = new Thief("thief_1",100,100, 25);
        expectedThief.setEquippedWeapon(testWeapons.get(3));
        var differentClass = new WhiteMage("differentClass",100,100, 25);
        differentClass.setEquippedWeapon(testWeapons.get(3));

        var staff = new Staff("regular_staff",15,10);
        IPlayerCharacter differentWeapon = new Thief("thief_2",100,100, 25);
        differentWeapon.equipWeapon(staff);

        ICharacter differentName = new Thief("differentName",100,100, 25);
        ICharacter differentCurrentHealth = new Thief("thief_3", 100,99, 25);
        ICharacter differentMaxHealth = new Thief("thief_3", 101,100, 25);
        ICharacter differentDefense = new Thief("thief_3", 100,100, 26);

        this.checkConstruction(testThief1, testIPlayerCharacter, testICharacter,
                expectedThief, differentClass, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);

        assertNotEquals(testThief1,enemy);
    }

    @Test
    void equipWeaponTest(){
        IPlayerCharacter testThief1 = new Thief("thief1",100,100, 25);
        IPlayerCharacter testThief2 = new Thief("thief2",100,100, 25);

        IWeapon testAxe = testWeapons.get(0);
        IWeapon testBow = testWeapons.get(1);
        IWeapon testKnife =  testWeapons.get(2);
        IWeapon testStaff = testWeapons.get(3);
        IWeapon testSword = testWeapons.get(4);

        checkEquipableWeapon(testSword,testThief1,testThief2);
        checkEquipableWeapon(testStaff,testThief1,testThief2);
        checkEquipableWeapon(testBow,testThief1,testThief2);

        checkNotEquipableWeapon(testAxe,testThief1,testThief2);
        checkNotEquipableWeapon(testKnife,testThief1,testThief2);
    }

    @Test
    void attackCharacterTest(){

        Thief thief1 = testThief1;
        Thief thief2 = testThiefLowHp;
        checkAttackCharacterMethod(thief1, thief2);
    }

    @Test
    void calculateWaitPeriodPlayerTest(){
        assertEquals(1,testThief1.calculateWaitPeriod());
    }
}
