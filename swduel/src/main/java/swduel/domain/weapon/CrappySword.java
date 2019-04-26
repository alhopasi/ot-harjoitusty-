package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;
import swduel.domain.ammunition.LightsaberSwing;

/**
 * Luokka Crappy Sword aseelle.
 *
 * @see swduel.domain.weapon.Weapon
 */
public class CrappySword extends Weapon {

    /**
     * Konstruktori syöttää aseen tiedot.
     */
    public CrappySword() {
        super("Crappy Sword", 100, 10, 10, 40, 300);
    }

    @Override
    public Ammunition getAmmo(int ammoX, int ammoY, int velocityX) {
        return new LightsaberSwing(ammoX, ammoY, 10, 40, velocityX, 0.07);
    }

    @Override
    public String getSoundEffectName() {
        return "sword";
    }
}
