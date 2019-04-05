package swduel.domain;

public abstract class Sprite {

    private double x;
    private double y;
    private int height;
    private int width;
    private double velocityX;
    private double velocityY;
    private int facing;

    public Sprite(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.facing = 0;
    }
    
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
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
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public String toString() {
        return "(" + x + ":" + y + ")" + "  " + this.velocityX + ":" + this.velocityY;
    }
    
    
}
