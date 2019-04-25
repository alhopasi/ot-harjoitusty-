package swduel.domain.weapon;

import java.util.Random;
import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LightsaberSwing;

public class Lightsaber extends Weapon {

    public Lightsaber() {
        super("Lightsaber", 10, 4, 10, 40, 300);
    }

    @Override
    public Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LightsaberSwing(ammoX, ammoY, 10, 40, velocityX, 0.07);
    }

    @Override
    public String getSoundEffectName() {
        return "lightsaber" + (new Random().nextInt(2) + 1);
    }
}
