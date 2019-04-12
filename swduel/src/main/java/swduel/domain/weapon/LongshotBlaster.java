package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

public class LongshotBlaster extends Weapon {
    
    public LongshotBlaster() {
        super("Longshot Blaster", 120, 6, 16, 5, 2000);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX, 2);
    }
    
}
