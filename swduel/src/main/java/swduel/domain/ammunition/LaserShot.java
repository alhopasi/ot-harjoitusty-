package swduel.domain.ammunition;

public class LaserShot extends Ammunition {

    public LaserShot(int x, int y, int ammoWidth, int ammoHeight, int velocityX) {
        super(x, y, ammoWidth, ammoHeight, "lasershot", velocityX, 2);
    }
}
