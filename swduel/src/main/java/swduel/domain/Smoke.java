package swduel.domain;

public class Smoke extends Sprite {

    private double aliveTime;

    public Smoke(int x, int y) {
        super(x, y, 10, 10);
        this.aliveTime = 0.10;
        super.setVelocity(0, 150);
    }

    @Override
    public void update(double time) {
        super.setY(super.getY() + super.getVelocityY() * time);
        this.aliveTime -= time;
    }
    
    public double getAliveTime() {
        return aliveTime;
    }

}
