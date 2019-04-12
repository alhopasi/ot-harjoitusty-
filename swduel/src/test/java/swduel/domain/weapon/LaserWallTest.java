
package swduel.domain.weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swduel.domain.ammunition.Ammunition;

public class LaserWallTest {

    Weapon saber;

    @Before
    public void setUp() {
        saber = new LaserWall();
    }

    @Test
    public void lightsaberNewAmmoCanBeCreated() {
        Ammunition ammo = saber.getNewAmmo(0, 0, 0);
        assertNotNull(ammo);
    }
}
