package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SwordTest extends AbstractWeaponTest {
    protected Sword testSword;

    @BeforeEach
    void setUp() {
        testSword = new Sword("test_sword",15,10);
        testIWeapon = new Sword("test_sword",15,10);
    }

    protected void checkConstructor(Sword testWeapon, IWeapon testIWeapon,Sword expectedWeapon, IWeapon differentName,
                                    IWeapon differentDamage, IWeapon differentWeight, IWeapon differentClass){
        assertEquals(expectedWeapon,testWeapon);
        assertEquals(expectedWeapon.hashCode(),testWeapon.hashCode());
        assertNotEquals(testWeapon,differentClass);
        super.checkConstructor(testIWeapon,differentName,differentDamage,differentWeight);
    }



    @Test
    void constructorTest() {
        Sword expectedWeapon = new Sword("test_sword",15,10);
        IWeapon differentName = new Sword("regular_sword",15,10);
        IWeapon differentDamage = new Sword("test_sword",16,10);
        IWeapon differentWeight = new Sword("test_sword",15,11);
        Bow differentClass = new Bow("test_bow",15,10);

        checkConstructor(testSword, testIWeapon, expectedWeapon, differentName, differentDamage, differentWeight,differentClass);
    }
}
