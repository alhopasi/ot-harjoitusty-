package swduel.domain;

public class Player {

    private int x;
    private int y;
    private int height;
    private int width;
    private double velocityX;
    private double velocityY;
    private double maxVelocity;
    private int facing;

    // location -1 when player is not on map (dead or not in the game yet)
    public Player() {
        this.x = -1;
        this.y = -1;
        this.height = 64;
        this.width = 32;
        this.maxVelocity = 300;
        this.facing = 0;
    }
    
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
        if (velocityX > maxVelocity) {
            velocityX = maxVelocity;
        }
        if (velocityX < -maxVelocity) {
            velocityX = -maxVelocity;
        }
    }
    
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }
    
    public double getVelocityX() {
        return velocityX;
    }
    
    public double getVelocityY() {
        return velocityY;
    }
    
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public int getFacing() {
        return facing;
    }
    
    public void update(double time) {
        x += velocityX * time;
        y += velocityY * time;
    }
    
    public int getX() {
        return (int) x;
    }
    
    public int getY() {
        return (int) y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public double getMaxVelocity() {
        return this.maxVelocity;
    }
    
    @Override
    public String toString() {
        return "(" + x + ":" + y + ")";
    }
    
    
}
