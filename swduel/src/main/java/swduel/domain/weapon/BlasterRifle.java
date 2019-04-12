package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

public class BlasterRifle extends Weapon {
    
    public BlasterRifle() {
        super("Blaster Rifle", 150, 5, 8, 2, 2000);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX);
    }
    
}
