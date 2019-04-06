package swduel.domain.ammunition;

import swduel.domain.Sprite;

public abstract class Ammunition extends Sprite {

    private String fileName;
    private double aliveTime;

    public Ammunition(int x, int y, int width, int height, String fileName, double initialSpeed, double aliveTime) {
        super(x, y, height, width);
        this.fileName = fileName;
        super.setVelocity(initialSpeed, 0);
        this.aliveTime = aliveTime;
    }
    
    public String getFileName() {
        return this.fileName;
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
        return super.toString() + "  " + this.fileName + "  " + this.aliveTime;
    }
}
