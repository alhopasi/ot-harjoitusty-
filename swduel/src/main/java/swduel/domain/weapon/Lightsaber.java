package swduel.domain.weapon;

import swduel.domain.ammunition.LightsaberSwing;

public class Lightsaber extends Weapon {
    
    public Lightsaber() {
        super("Lightsaber", 1000, 5, new LightsaberSwing(-1, -1));
    }
}
