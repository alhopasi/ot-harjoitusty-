
package swduel.domain.weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swduel.domain.ammunition.Ammunition;

public class CrappySwordTest {

    Weapon sword;

    @Before
    public void setUp() {
        sword = new CrappySword();
    }

    @Test
    public void lightsaberNewAmmoCanBeCreated() {
        Ammunition ammo = sword.getNewAmmo(0, 0, 0);
        assertNotNull(ammo);
    }
}
