package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LightsaberSwing;

/**
 * Luokka Laser Wall aseelle.
 *
 * @see swduel.domain.weapon.Weapon
 */
public class LaserWall extends Weapon {

    /**
     * Konstruktori syöttää aseen tiedot.
     */
    public LaserWall() {
        super("Laser Wall", 100, 7, 10, 40, 500);
    }

    @Override
    public Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LightsaberSwing(ammoX, ammoY, 10, 40, velocityX, 2);
    }

    @Override
    public String getSoundEffectName() {
        return "laserwall";
    }
}
