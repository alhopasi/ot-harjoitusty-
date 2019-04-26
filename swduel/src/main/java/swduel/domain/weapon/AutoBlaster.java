
package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LaserShot;

/**
 * Luokka AutoBlaster aseelle.
 * @see swduel.domain.weapon.Weapon
 */
public class AutoBlaster extends Weapon {

    /**
     * Konstruktori syöttää aseen tiedot.
     */
    public AutoBlaster() {
        super("Auto Blaster", 30, 3, 16, 5, 1000);
    }
    
    @Override
    protected Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LaserShot(ammoX, ammoY, 16, 5, velocityX, 2);
    }
    
    @Override
    public String getSoundEffectName() {
        return "lasershot";
    }
}
