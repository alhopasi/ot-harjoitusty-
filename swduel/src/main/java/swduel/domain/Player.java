package swduel.domain;

import swduel.domain.weapon.Weapon;

public class Player extends Sprite {

    private double maxVelocity;
    private Weapon weapon;

    // location -1 when player is not on map (dead or not in the game yet)
    public Player() {
        super(-1, -1, 64, 32);
        this.maxVelocity = 300;
    }
    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void addVelocity(double x, double y) {
        super.addVelocity(x, y);
        if (super.getVelocityX() > maxVelocity) {
            super.setVelocity(maxVelocity, super.getVelocityY());
        }
        if (super.getVelocityX() < -maxVelocity) {
            super.setVelocity(-maxVelocity, super.getVelocityY());
        }
    }

    public double getMaxVelocity() {
        return this.maxVelocity;
    }
    
    @Override
    public String toString() {
        return super.toString() + "  " + weapon;
    }
}
