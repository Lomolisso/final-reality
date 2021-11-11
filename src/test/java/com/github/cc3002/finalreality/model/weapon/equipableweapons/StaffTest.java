package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StaffTest extends AbstractWeaponTest {
    protected Staff testStaff;

    @BeforeEach
    void setUp() {
        testStaff = new Staff("test_staff",15,10);
        testIWeapon = new Staff("test_staff",15,10);
    }

    protected void checkConstructor(Staff testWeapon, IWeapon testIWeapon, Staff expectedWeapon, IWeapon differentName,
                                    IWeapon differentDamage, IWeapon differentWeight, IWeapon differentClass){
        assertEquals(expectedWeapon,testWeapon);
        assertEquals(expectedWeapon.hashCode(),testWeapon.hashCode());
        assertNotEquals(testWeapon,differentClass);
        super.checkConstructor(testIWeapon,differentName,differentDamage,differentWeight);
    }



    @Test
    void constructorTest() {
        Staff expectedWeapon = new Staff("test_staff",15,10);
        IWeapon differentName = new Staff("regular_staff",15,10);
        IWeapon differentDamage = new Staff("test_staff",16,10);
        IWeapon differentWeight = new Staff("test_staff",15,11);
        Bow differentClass = new Bow("test_bow",15,10);

        checkConstructor(testStaff, testIWeapon, expectedWeapon, differentName, differentDamage, differentWeight,differentClass);
    }
}
