package swduel.domain;

import javafx.scene.paint.Color;

public class Player {

//   private Weapon weapon;
    private int score;
    private int x;
    private int y;
    private Color color;

    // location -1 when player is not on map (dead or not in the game yet)
    public Player(Color color) {
        this.color = color;
        this.x = -1;
        this.y = -1;
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
    
    public Color getColor() {
        return color;
    }
    
}
