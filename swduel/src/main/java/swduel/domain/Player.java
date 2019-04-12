package swduel.domain;

import swduel.domain.weapon.Weapon;

public class Player extends Sprite {

    private double maxVelocity;
    private Weapon weapon;
    private int id;
    private int score;

    // location -1 when player is not on map (dead or not in the game yet)
    public Player(int id) {
        super(-1, -1, 64, 32);
        this.id = id;
        this.maxVelocity = 300;
        this.score = 0;
    }
    
    public void addScore() {
        score++;
    }
    
    public int getScore() {
        return score;
    }

    public int getId() {
        return this.id;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public void update(double time) {
        super.update(time);
        if (weapon != null) {
            weapon.lowerCooldown(time);
        }
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
        return super.toString() + "  weapon: " + weapon;
    }
}
