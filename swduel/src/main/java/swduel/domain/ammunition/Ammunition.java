package swduel.domain.ammunition;

import swduel.domain.Sprite;

public abstract class Ammunition extends Sprite {

    private String name;
    private double aliveTime;

    public Ammunition(int x, int y, int width, int height, String name, double initialSpeed, double aliveTime) {
        super(x, y, height, width);
        this.name = name;
        super.setVelocity(initialSpeed, 0);
        this.aliveTime = aliveTime;
    }

    public String getName() {
        return this.name;
    }

    public double getAliveTime() {
        return this.aliveTime;
    }

    @Override
    public void update(double time) {
        super.setX(super.getX() + super.getVelocityX() * time);
        this.aliveTime -= time;
    }
    
    @Override
    public String toString() {
        return super.toString() + "  " + this.name + "  " + this.aliveTime;
    }
}
