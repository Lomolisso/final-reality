package com.github.cc3002.finalreality.model.weapon.equipableweapons;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AxeTest extends AbstractWeaponTest {
    protected Axe testAxe;

    @BeforeEach
    void setUp() {
        testAxe = new Axe("test_axe",15,10);
        testIWeapon = new Axe("test_axe",15,10);
    }

    protected void checkConstructor(Axe testWeapon,IWeapon testIWeapon, Axe expectedWeapon, IWeapon differentName,
                                    IWeapon differentDamage, IWeapon differentWeight, IWeapon differentClass){

        assertEquals(expectedWeapon,testWeapon);
        assertEquals(expectedWeapon.hashCode(),testWeapon.hashCode());
        assertNotEquals(testWeapon,differentClass);
        super.checkConstructor(testIWeapon,differentName,differentDamage,differentWeight);
    }



    @Test
    void constructorTest() {
        Axe expectedWeapon = new Axe("test_axe",15,10);
        IWeapon differentName = new Axe("regular_axe",15,10);
        IWeapon differentDamage = new Axe("test_axe",16,10);
        IWeapon differentWeight = new Axe("test_axe",15,11);
        Bow differentClass = new Bow("test_bow",15,10);

        checkConstructor(testAxe, testIWeapon, expectedWeapon, differentName, differentDamage, differentWeight, differentClass);
    }
}
