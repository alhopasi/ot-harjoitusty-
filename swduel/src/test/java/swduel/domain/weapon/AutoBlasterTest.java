
package swduel.domain.weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swduel.domain.ammunition.Ammunition;

public class AutoBlasterTest {

    Weapon blaster;

    @Before
    public void setUp() {
        blaster = new AutoBlaster();
    }

    @Test
    public void autoblasterCanBeCreated() {
        assertNotNull(blaster);
    }

    @Test
    public void weaponLevelEquals() {
        assertEquals(3, blaster.getLevel());
    }

    @Test
    public void weaponCooldownEquals() {
        assertEquals(0, blaster.getCooldown());
    }

    @Test
    public void weaponCooldownCanBeIncreased() {
        blaster.addCooldown();
        assertEquals(30, blaster.getCooldown());
    }
    
    @Test
    public void weaponCooldownCanBeLowered() {
        blaster.addCooldown();
        blaster.lowerCooldown(10);
        assertEquals(20, blaster.getCooldown());
        blaster.lowerCooldown(30);
        assertEquals(0, blaster.getCooldown());
    }

    @Test
    public void autoBlasterNewAmmoCanBeCreated() {
        Ammunition ammo = blaster.getNewAmmo(0, 0, 0);
        assertNotNull(ammo);
        Ammunition ammo2 = blaster.getNewAmmo(0, 0, 1);
        assertNotNull(ammo2);
    }
    
    @Test
    public void weaponToStringReturnsName() {
        assertEquals("Auto Blaster", blaster.toString());
    }
}
