package com.github.cc3002.finalreality.model.character.playablecharacters.magic;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WhiteMageTest extends AbstractMageTest{
    protected WhiteMage testWhiteMage1;
    protected WhiteMage testWhiteMageLowHp;

    @BeforeEach
    void setUp(){
        super.basicSetUp();

        testWhiteMage1 = new WhiteMage("white_mage_1",100,100, 25);
        testWhiteMage1.setEquippedWeapon(testWeapons.get(3));
        testWhiteMageLowHp = new WhiteMage("low_hp",100,15, 0);
        testWhiteMageLowHp.setEquippedWeapon(testWeapons.get(3));
        testIPlayerCharacter = new WhiteMage("white_mage_2",100,100, 25);
        testIPlayerCharacter.setEquippedWeapon(testWeapons.get(3));
        testICharacter = new WhiteMage("white_mage_3",100,100, 25);


    }


    protected void checkConstruction(WhiteMage testWhiteMage1, IPlayerCharacter testWhiteMage2,
                                     ICharacter testWhiteMage3, WhiteMage expectedWhiteMage1,
                                     BlackMage differentClass, IPlayerCharacter differentWeapon,
                                     ICharacter differentName, ICharacter differentCurrentHealth,
                                     ICharacter differentMaxHealth ,ICharacter differentDefense){

        assertEquals(testWhiteMage1,testWhiteMage1);
        assertEquals(expectedWhiteMage1,testWhiteMage1);
        assertNotEquals(testWhiteMage1,differentClass);
        assertEquals(expectedWhiteMage1.hashCode(),testWhiteMage1.hashCode());
        super.checkConstruction(testWhiteMage2, testWhiteMage3, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);
    }

    @Test
    void constructorTest(){
        var enemy = new Enemy("enemy",10,100,100, 25, 10,"img\\Apocalypse.png");
        var expectedWhiteMage = new WhiteMage("white_mage_1",100,100, 25);
        expectedWhiteMage.setEquippedWeapon(testWeapons.get(3));
        var differentClass = new BlackMage("differentClass",100,100, 25);
        differentClass.setEquippedWeapon(testWeapons.get(3));

        var staff = new Staff("regular_staff",15,10);
        IPlayerCharacter differentWeapon = new WhiteMage("white_mage_2",100,100, 25);
        differentWeapon.setEquippedWeapon(staff);

        ICharacter differentName = new WhiteMage("differentName",100,100, 25);
        ICharacter differentCurrentHealth = new WhiteMage("white_mage_3", 100,99, 25);
        ICharacter differentMaxHealth = new WhiteMage("white_mage_3", 101,100, 25);
        ICharacter differentDefense = new WhiteMage("white_mage_3", 100,100, 26);

        this.checkConstruction(testWhiteMage1, testIPlayerCharacter, testICharacter,
                expectedWhiteMage, differentClass, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);

        assertNotEquals(testWhiteMage1,enemy);
    }

    @Test
    void equipWeaponTest(){
        IPlayerCharacter testWhiteMage1 = new WhiteMage("white_mage_1",100,100, 25);
        IPlayerCharacter testWhiteMage2 = new WhiteMage("white_mage_2",100,100, 25);

        IWeapon testAxe = testWeapons.get(0);
        IWeapon testBow = testWeapons.get(1);
        IWeapon testKnife =  testWeapons.get(2);
        IWeapon testStaff = testWeapons.get(3);
        IWeapon testSword = testWeapons.get(4);

        checkEquipableWeapon(testStaff,testWhiteMage1,testWhiteMage2);

        checkNotEquipableWeapon(testAxe,testWhiteMage1,testWhiteMage2);
        checkNotEquipableWeapon(testKnife,testWhiteMage1,testWhiteMage2);
        checkNotEquipableWeapon(testSword,testWhiteMage1,testWhiteMage2);
        checkNotEquipableWeapon(testBow,testWhiteMage1,testWhiteMage2);
    }

    @Test
    void attackCharacterTest(){
        WhiteMage whiteMage1 = testWhiteMage1;
        WhiteMage whiteMage2 = testWhiteMageLowHp;
        checkAttackCharacterMethod(whiteMage1, whiteMage2);
    }

    @Test
    void calculateWaitPeriodPlayerTest(){
        assertEquals(1,testWhiteMage1.calculateWaitPeriod());
    }
}
