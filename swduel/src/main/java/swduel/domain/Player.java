package swduel.domain;

public class Player {

//   private Weapon weapon;
    private int score;
    private int x;
    private int y;
    private int height;
    private int width;

    // location -1 when player is not on map (dead or not in the game yet)
    public Player() {
        this.x = -1;
        this.y = -1;
        this.height = 64;
        this.width = 32;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public String toString() {
        return "(" + x + ":" + y + ")";
    }
    
}
