package com.github.cc3002.finalreality.model.character.playablecharacters;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playablecharacters.magic.BlackMage;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.equipableweapons.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
    protected IPlayerCharacter testIPlayerCharacter;
    protected List<IWeapon> testWeapons;
    protected HashMap<Integer,IWeapon> testInventory1;
    protected HashMap<Integer,IWeapon> testInventory2;

    protected void checkConstruction(final IPlayerCharacter testCharacter1, final ICharacter testCharacter2,
                                     final IPlayerCharacter differentWeapon, final ICharacter differentName,
                                     final ICharacter differentMaxHealth, final ICharacter differentCurrentHealth,
                                     final ICharacter differentDefense) {
        assertNotEquals(testCharacter1,differentWeapon);
        super.checkConstruction(testCharacter2,differentName,differentCurrentHealth,differentMaxHealth,differentDefense);
    }

    protected void checkNotEquipableWeapon(IWeapon weapon, IPlayerCharacter playerCharacter1, IPlayerCharacter playerCharacter2){
        playerCharacter1.setEquippedWeapon(weapon);
        playerCharacter2.equipWeapon(weapon);
        assertNotEquals(playerCharacter1.getEquippedWeapon(),playerCharacter2.getEquippedWeapon());
    }

    protected void checkEquipableWeapon(IWeapon weapon, IPlayerCharacter playerCharacter1, IPlayerCharacter playerCharacter2){
        playerCharacter1.setEquippedWeapon(weapon);
        playerCharacter2.equipWeapon(weapon);
        assertEquals(playerCharacter1.getEquippedWeapon(),playerCharacter2.getEquippedWeapon());
    }
    protected void basicSetUp(){
        testWeapons = new ArrayList<>();
        testInventory1 = new HashMap<Integer,IWeapon>();
        testInventory2 = new HashMap<Integer,IWeapon>();
        testWeapons.add(new Axe("test_Axe",50,10));
        testWeapons.add(new Bow("test_Bow",50,10));
        testWeapons.add(new Knife("test_Knife",50,10));
        testWeapons.add(new Staff("test_Staff",50,10));
        testWeapons.add(new Sword("test_Sword",50,10));
    }
    @Test
    void noWeaponAttackTest(){
        BlackMage dummy1 = new BlackMage("dummy1",100,100,100);
        BlackMage dummy2 = new BlackMage("dummy2",100,100,100);
        dummy1.attackCharacter(dummy2);
        assertEquals(100,dummy2.getCurrentHealthPoints());
    }
}
