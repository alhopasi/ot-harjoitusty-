package swduel.domain;

/**
 * Luokka käsittelee rakettirepun piirrettävää savua
 * @author alhopasi
 */
public class Smoke extends Sprite {

    private double aliveTime;

    /**
     * Konstruktori luo Smoke-spriten
     * @param x Spriten vasen reuna
     * @param y Spriten alareuna
     */
    public Smoke(int x, int y) {
        super(x, y, 10, 10);
        this.aliveTime = 0.10;
        super.setVelocity(0, 150);
    }

    /**
     * Päivittää savun x ja y muuttujia sekä vähentää alive-timeä
     * @param time aika joka on kulunut viimeisimmästä päivityksestä
     */
    @Override
    public void update(double time) {
        super.setY(super.getY() + super.getVelocityY() * time);
        this.aliveTime -= time;
    }
    
    public double getAliveTime() {
        return aliveTime;
    }

}
