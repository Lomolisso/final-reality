package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.equipableweapons.Staff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public abstract class AbstractWeaponTest {
  protected IWeapon testIWeapon;

  protected void checkConstructor(IWeapon testWeapon, IWeapon differentName,
                                  IWeapon differentDamage, IWeapon differentWeight){

    assertNotEquals(testWeapon,differentName);
    assertNotEquals(testWeapon,differentDamage);
    assertNotEquals(testWeapon,differentWeight);
  }
}
