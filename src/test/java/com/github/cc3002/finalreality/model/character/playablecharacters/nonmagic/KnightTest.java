package com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.AbstractPlayerCharacterTest;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.Axe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnightTest extends AbstractPlayerCharacterTest {
    protected Knight testKnight1;
    protected Knight testKnightLowHp;

    @BeforeEach
    void setUp(){
        super.basicSetUp();

        testKnight1 = new Knight("knight_1",100,100, 25);
        testKnight1.setEquippedWeapon(testWeapons.get(0));
        testKnightLowHp = new Knight("low_hp",100,15, 0);
        testKnightLowHp.setEquippedWeapon(testWeapons.get(0));
        testIPlayerCharacter = new Knight("knight_2",100,100, 25);
        testIPlayerCharacter.setEquippedWeapon(testWeapons.get(0));
        testICharacter = new Knight("knight_3",100,100, 25);
    }


    protected void checkConstruction(Knight testKnight1, IPlayerCharacter testKnight2,
                                     ICharacter testKnight3, Knight expectedKnight1,
                                     Engineer differentClass, IPlayerCharacter differentWeapon,
                                     ICharacter differentName, ICharacter differentCurrentHealth,
                                     ICharacter differentMaxHealth, ICharacter differentDefense){

        assertEquals(testKnight1,testKnight1);
        assertEquals(expectedKnight1,testKnight1);
        assertNotEquals(testKnight1,differentClass);
        assertEquals(expectedKnight1.hashCode(),testKnight1.hashCode());
        super.checkConstruction(testKnight2, testKnight3, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);
    }

    @Test
    void constructorTest(){
        var enemy = new Enemy("enemy", 10,100,100, 25, 10,"img\\Apocalypse.png");
        var expectedKnight = new Knight("knight_1",100,100, 25);
        expectedKnight.setEquippedWeapon(testWeapons.get(0));
        var differentClass = new Engineer("differentClass",100,100, 25);
        differentClass.setEquippedWeapon(testWeapons.get(0));
        var axe = new Axe("regular_axe",15,10);
        IPlayerCharacter differentWeapon = new Knight("knight_2",100,100, 25);
        differentWeapon.equipWeapon(axe);

        ICharacter differentName = new Knight("differentName",100,100, 25);
        ICharacter differentCurrentHealth = new Knight("knight_3", 100,99, 25);
        ICharacter differentMaxHealth = new Knight("knight_3", 101,100, 25);
        ICharacter differentDefense = new Knight("knight_3", 100,100, 26);

        this.checkConstruction(testKnight1, testIPlayerCharacter, testICharacter,
                expectedKnight, differentClass, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);

        assertNotEquals(testKnight1,enemy);
    }

    @Test
    void equipWeaponTest(){
        IPlayerCharacter testKnight1 = new Knight("knight1",100,100, 25);
        IPlayerCharacter testKnight2 = new Knight("knight2",100,100, 25);

        IWeapon testAxe = testWeapons.get(0);
        IWeapon testBow = testWeapons.get(1);
        IWeapon testKnife =  testWeapons.get(2);
        IWeapon testStaff = testWeapons.get(3);
        IWeapon testSword = testWeapons.get(4);

        checkEquipableWeapon(testSword,testKnight1,testKnight2);
        checkEquipableWeapon(testAxe,testKnight1,testKnight2);
        checkEquipableWeapon(testKnife,testKnight1,testKnight2);

        checkNotEquipableWeapon(testBow,testKnight1,testKnight2);
        checkNotEquipableWeapon(testStaff,testKnight1,testKnight2);
    }

    @Test
    void attackCharacterTest(){
        Knight knight1 = testKnight1;
        Knight knight2 = testKnightLowHp;
        checkAttackCharacterMethod(knight1, knight2);
    }

    @Test
    void calculateWaitPeriodPlayerTest(){
        assertEquals(1,testKnight1.calculateWaitPeriod());
    }
}
