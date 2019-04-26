package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

/**
 * Luokka Laser Wall aseelle.
 *
 * @see swduel.domain.weapon.Weapon
 */
public class LongshotBlaster extends Weapon {

    /**
     * Konstruktori syöttää aseen tiedot.
     */
    public LongshotBlaster() {
        super("Longshot Blaster", 120, 6, 16, 5, 2000);
    }

    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX, 2);
    }

    @Override
    public String getSoundEffectName() {
        return "lasershotBig";
    }
}
