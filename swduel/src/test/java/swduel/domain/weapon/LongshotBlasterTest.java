
package swduel.domain.weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swduel.domain.ammunition.Ammunition;
public class LongshotBlasterTest {
    
    Weapon blaster;
    
    @Before
    public void setUp() {
        blaster = new LongshotBlaster();
    }
    
     @Test
     public void longshotBlasterNewAmmoCanBeCreated() {
        Ammunition ammo = blaster.getNewAmmo(0, 0, 0);
        assertNotNull(ammo);
     }
}
