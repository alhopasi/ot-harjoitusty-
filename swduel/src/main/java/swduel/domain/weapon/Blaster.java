package swduel.domain.weapon;

import swduel.domain.ammunition.LaserShot;

public class Blaster extends Weapon {

    public Blaster() {
        super("Blaster", 300, 5, new LaserShot(-1, -1));
    }
}
