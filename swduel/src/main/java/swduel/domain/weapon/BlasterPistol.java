package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

public class BlasterPistol extends Weapon {
    
    public BlasterPistol() {
        super("Blaster Pistol", 120, 8, 16, 5, 800);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX, 2);
    }
    
}
