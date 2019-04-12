package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LightsaberSwing;

public class LaserWall extends Weapon {
    
    public LaserWall() {
        super("Laser Wall", 100, 7, 10, 40, 500);
    }
    
    @Override
    public Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LightsaberSwing(ammoX, ammoY, 10, 40, velocityX, 2);
    }
}
