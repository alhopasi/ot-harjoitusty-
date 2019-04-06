package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

public class Blaster extends Weapon {

    public Blaster() {
        super("blaster", 70, 5, 8, 2, 600);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, velocityX);
    }
    
}
