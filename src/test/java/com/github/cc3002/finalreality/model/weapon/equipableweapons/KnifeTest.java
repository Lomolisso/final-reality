package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnifeTest extends AbstractWeaponTest {
    protected Knife testKnife;

    @BeforeEach
    void setUp() {
        testKnife = new Knife("test_knife",15,10);
        testIWeapon = new Knife("test_knife",15,10);
    }

    protected void checkConstructor(Knife testWeapon, IWeapon testIWeapon, Knife expectedWeapon, IWeapon differentName,
                                    IWeapon differentDamage, IWeapon differentWeight, IWeapon differentClass){
        assertEquals(expectedWeapon,testWeapon);
        assertEquals(expectedWeapon.hashCode(),testWeapon.hashCode());
        assertNotEquals(testWeapon,differentClass);
        super.checkConstructor(testIWeapon,differentName,differentDamage,differentWeight);
    }



    @Test
    void constructorTest() {
        Knife expectedWeapon = new Knife("test_knife",15,10);
        IWeapon differentName = new Knife("regular_knife",15,10);
        IWeapon differentDamage = new Knife("test_knife",16,10);
        IWeapon differentWeight = new Knife("test_knife",15,11);
        Bow differentClass = new Bow("test_bow",15,10);

        checkConstructor(testKnife, testIWeapon, expectedWeapon, differentName, differentDamage, differentWeight,differentClass);
    }
}
