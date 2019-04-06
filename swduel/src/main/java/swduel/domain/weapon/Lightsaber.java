package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LightsaberSwing;

public class Lightsaber extends Weapon {
    
    public Lightsaber() {
        super("Lightsaber", 10, 7, 10, 40, 300);
    }
    
    @Override
    public Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LightsaberSwing(ammoX, ammoY, 10, 40, velocityX);
    }
}
