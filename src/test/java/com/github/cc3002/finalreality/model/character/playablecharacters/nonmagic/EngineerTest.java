package com.github.cc3002.finalreality.model.character.playablecharacters.nonmagic;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.AbstractPlayerCharacterTest;
import com.github.cc3002.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EngineerTest extends AbstractPlayerCharacterTest {
    protected Engineer testEngineer1;
    protected Engineer testEngineerLowHp;

    @BeforeEach
    void setUp(){
        super.basicSetUp();

        testEngineer1 = new Engineer("engineer_1",100,100, 25);
        testEngineer1.setEquippedWeapon(testWeapons.get(0));
        testEngineerLowHp = new Engineer("low_hp",100,15, 0);
        testEngineerLowHp.setEquippedWeapon(testWeapons.get(0));
        testIPlayerCharacter = new Engineer("engineer_2",100,100, 25);
        testIPlayerCharacter.setEquippedWeapon(testWeapons.get(0));
        testICharacter = new Engineer("engineer_3",100,100, 25);
    }

    protected void checkConstruction(Engineer testEngineer1, IPlayerCharacter testEngineer2,
                                     ICharacter testEngineer3, Engineer expectedEngineer1,
                                     Knight differentClass, IPlayerCharacter differentWeapon,
                                     ICharacter differentName, ICharacter differentCurrentHealth,
                                     ICharacter differentMaxHealth, ICharacter differentDefense){

        assertEquals(testEngineer1,testEngineer1);
        assertEquals(expectedEngineer1,testEngineer1);
        assertNotEquals(testEngineer1,differentClass);
        assertEquals(expectedEngineer1.hashCode(),testEngineer1.hashCode());
        super.checkConstruction(testEngineer2, testEngineer3, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);
    }

    @Override
    protected void basicSetUp() {
        super.basicSetUp();
    }

    @Test
    void constructorTest(){
        var enemy = new Enemy("enemy",10,100,100, 25, 10,"img\\Apocalypse.png");
        var expectedEngineer = new Engineer("engineer_1",100,100, 25);
        expectedEngineer.setEquippedWeapon(testWeapons.get(0));
        var differentClass = new Knight("differentClass",100,100, 25);
        differentClass.setEquippedWeapon(testWeapons.get(0));

        var axe = new Axe("regular_axe",15,10);
        IPlayerCharacter differentWeapon = new Engineer("engineer_2",100,100, 25);
        differentWeapon.setEquippedWeapon(axe);

        ICharacter differentName = new Engineer("differentName",100,100, 25);
        ICharacter differentCurrentHealth = new Engineer("engineer_3", 100,99, 25);
        ICharacter differentMaxHealth = new Engineer("engineer_3", 101,100, 25);
        ICharacter differentDefense = new Engineer("engineer_3", 100,100, 26);

        this.checkConstruction(testEngineer1, testIPlayerCharacter, testICharacter,
                expectedEngineer, differentClass, differentWeapon, differentName, differentCurrentHealth, differentMaxHealth, differentDefense);

        assertNotEquals(testEngineer1,enemy);
    }

    @Test
    void equipWeaponTest(){
        IPlayerCharacter testEngineer1 = new Engineer("engineer1",100,100, 25);
        IPlayerCharacter testEngineer2 = new Engineer("engineer2",100,100, 25);

        IWeapon testAxe = testWeapons.get(0);
        IWeapon testBow = testWeapons.get(1);
        IWeapon testKnife =  testWeapons.get(2);
        IWeapon testStaff = testWeapons.get(3);
        IWeapon testSword = testWeapons.get(4);

        checkEquipableWeapon(testAxe,testEngineer1,testEngineer2);
        checkEquipableWeapon(testBow,testEngineer1,testEngineer2);

        checkNotEquipableWeapon(testKnife,testEngineer1,testEngineer2);
        checkNotEquipableWeapon(testSword,testEngineer1,testEngineer2);
        checkNotEquipableWeapon(testStaff,testEngineer1,testEngineer2);
    }

    @Test
    void attackCharacterTest(){
        Engineer engineer1 = testEngineer1;
        Engineer engineer2 = testEngineerLowHp;
        checkAttackCharacterMethod(engineer1, engineer2);
    }

    @Test
    void calculateWaitPeriodPlayerTest(){
        assertEquals(1,testEngineer1.calculateWaitPeriod());
    }
}

