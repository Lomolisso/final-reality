package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BowTest extends AbstractWeaponTest {

    protected Bow testBow;

    @BeforeEach
    void setUp() {
        testBow = new Bow("test_bow",15,10);
        testIWeapon = new Bow("test_bow",15,10);
    }

    protected void checkConstructor(Bow testWeapon, IWeapon testIWeapon, Bow expectedWeapon, IWeapon differentName,
                                    IWeapon differentDamage, IWeapon differentWeight, IWeapon differentClass){
        assertEquals(expectedWeapon,testWeapon);
        assertEquals(expectedWeapon.hashCode(),testWeapon.hashCode());
        assertNotEquals(testWeapon,differentClass);
        super.checkConstructor(testIWeapon,differentName,differentDamage,differentWeight);
    }



    @Test
    void constructorTest() {
        Bow expectedWeapon = new Bow("test_bow",15,10);
        IWeapon differentName = new Bow("regular_bow",15,10);
        IWeapon differentDamage = new Bow("test_bow",16,10);
        IWeapon differentWeight = new Bow("test_bow",15,11);
        Axe differentClass = new Axe("test_axe",15,10);

        checkConstructor(testBow, testIWeapon, expectedWeapon, differentName, differentDamage, differentWeight, differentClass);
    }
}
