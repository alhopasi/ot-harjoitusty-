package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

public class AutoBlaster extends Weapon {

    public AutoBlaster() {
        super("Auto Blaster", 30, 3, 8, 2, 1000);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX);
    }
    
}
