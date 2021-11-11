package com.github.cc3002.finalreality.model.character.playablecharacters.magic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;

public class BlackMageTest extends AbstractMageTest {
    protected BlackMage testBlackMage1;
    protected BlackMage testBlackMageLowHp;

    @BeforeEach
    void setUp(){
        super.basicSetUp();

        testBlackMage1 = new BlackMage("black_mage_1",100,100, 25);
        testBlackMage1.setEquippedWeapon(testWeapons.get(3));
        testBlackMageLowHp = new BlackMage("low_hp",100,15, 0);
        testBlackMageLowHp.setEquippedWeapon(testWeapons.get(3));
        testIPlayerCharacter = new BlackMage("black_mage_2",100,100, 25);
        testIPlayerCharacter.setEquippedWeapon(testWeapons.get(3));
        testICharacter = new BlackMage("black_mage_3",100,100, 25);
    }


    protected void checkConstruction(BlackMage testBlackMage1, IPlayerCharacter testBlackMage2,
                                     ICharacter testBlackMage3, BlackMage expectedBlackMage1,
                                     WhiteMage differentClass, IPlayerCharacter differentWeapon,
                                     ICharacter differentName, ICharacter differentCurrentHealth,
                                     ICharacter differentMaxHealth, ICharacter differentDefense){

        assertEquals(testBlackMage1,testBlackMage1);
        assertEquals(expectedBlackMage1,testBlackMage1);
        assertNotEquals(testBlackMage1,differentClass);
        assertEquals(expectedBlackMage1.hashCode(),testBlackMage1.hashCode());
        super.checkConstruction(testBlackMage2, testBlackMage3, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);
    }

    @Test
    void constructorTest(){
        var enemy = new Enemy("enemy",10,100,100, 25, 10,"img\\Apocalypse.png");
        var expectedBlackMage = new BlackMage("black_mage_1",100,100, 25);
        expectedBlackMage.setEquippedWeapon(testWeapons.get(3));
        var differentClass = new WhiteMage("differentClass",100,100, 25);
        differentClass.setEquippedWeapon(testWeapons.get(3));

        var staff = new Staff("regular_staff",15,10);
        IPlayerCharacter differentWeapon = new BlackMage("black_mage_2",100,100, 25);
        differentWeapon.equipWeapon(staff);

        ICharacter differentName = new BlackMage("differentName",100,100, 25);
        ICharacter differentCurrentHealth = new BlackMage("black_mage_3", 100,99, 25);
        ICharacter differentMaxHealth = new BlackMage("black_mage_3", 101,100, 25);
        ICharacter differentDefense = new BlackMage("black_mage_3", 100,100, 26);

        this.checkConstruction(testBlackMage1, testIPlayerCharacter, testICharacter,
                expectedBlackMage, differentClass, differentWeapon, differentName, differentCurrentHealth,differentMaxHealth, differentDefense);

        assertNotEquals(testBlackMage1,enemy);
    }

    @Test
    void equipWeaponTest(){
        IPlayerCharacter testBlackMage1 = new BlackMage("black_mage_1",100,100, 25);
        IPlayerCharacter testBlackMage2 = new BlackMage("black_mage_2",100,100, 25);

        IWeapon testAxe = testWeapons.get(0);
        IWeapon testBow = testWeapons.get(1);
        IWeapon testKnife =  testWeapons.get(2);
        IWeapon testStaff = testWeapons.get(3);
        IWeapon testSword = testWeapons.get(4);

        checkEquipableWeapon(testStaff,testBlackMage1,testBlackMage2);
        checkEquipableWeapon(testKnife,testBlackMage1,testBlackMage2);

        checkNotEquipableWeapon(testSword,testBlackMage1,testBlackMage2);
        checkNotEquipableWeapon(testBow,testBlackMage1,testBlackMage2);
        checkNotEquipableWeapon(testAxe,testBlackMage1,testBlackMage2);
    }

    @Test
    void attackCharacterTest(){
        BlackMage blackMage1 = testBlackMage1;
        BlackMage blackMage2 = testBlackMageLowHp;
        checkAttackCharacterMethod(blackMage1, blackMage2);
    }

    @Test
    void calculateWaitPeriodPlayerTest(){
        assertEquals(1,testBlackMage1.calculateWaitPeriod());
    }
}
