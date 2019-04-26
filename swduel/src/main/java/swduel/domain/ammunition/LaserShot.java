package swduel.domain.ammunition;

/**
 * Luokka laser-ammukselle
 * @see swduel.domain.ammunition.Ammunition
 */
public class LaserShot extends Ammunition {

    /**
     * Konstruktori luo uuden ammuksen sille annettujen parametrien perusteella
     * @param x Ammuksen vasemman reunan sijainti
     * @param y Ammuksen alareunan sijainti
     * @param ammoWidth Ammuksen leveys
     * @param ammoHeight Ammuksen korkeus
     * @param velocityX Ammuksen nopeus
     * @param aliveTime Ammuksen elossaoloaika
     */
    public LaserShot(int x, int y, int ammoWidth, int ammoHeight, int velocityX, double aliveTime) {
        super(x, y, ammoWidth, ammoHeight, "lasershot", velocityX, aliveTime);
    }
}
