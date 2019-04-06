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

    public boolean collides(Sprite other) {
        if (this == other) {
            System.out.println("return");
            return false;
        }
        double myBottom = this.y;
        double myTop = this.y - this.height;
        double myLeft = this.x;
        double myRight = this.x + this.width;

        double otherBottom = other.getY();
        double otherTop = other.getY() - other.getHeight();
        double otherLeft = other.getX();
        double otherRight = other.getX() + other.getWidth();
        
        if ((myBottom >= otherTop && myBottom <= otherBottom || myTop >= otherTop && myTop <= otherBottom)
                && (myRight <= otherRight && myRight >= otherLeft || myLeft <= otherRight && myLeft >= otherLeft)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ":" + y + ")" + "  " + this.velocityX + ":" + this.velocityY;
    }

}
