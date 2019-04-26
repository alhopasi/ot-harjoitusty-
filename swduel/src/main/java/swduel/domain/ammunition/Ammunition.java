package swduel.domain.ammunition;

import swduel.domain.Sprite;

/**
 * Abstrakti luokka ammukselle.
 * @see swduel.domain.Sprite
 */
public abstract class Ammunition extends Sprite {

    private String fileName;
    private double aliveTime;

    /**
     * Konstruktorille annetaan ammuksen sijainti, leveys ja korkeus, kuvan tiedostonimi, lähtönopeus sekä aika jonka ammus on elossa.
     * @param x Ammuksen vasemman reunan sijainti
     * @param y Ammuksen alareunan sijainti
     * @param width Ammuksen leveys
     * @param height Ammuksen korkeus
     * @param fileName Ammuksen kuvan tiedostonimi
     * @param initialSpeed Ammuksen lähtönopeus
     * @param aliveTime Aika jonka ammus on elossa
     */
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

    /**
     * Päivittää sijaintia ja vähentää elossaoloaikaa.
     * @see swduel.domain.Sprite#update(double) 
     * @param time 
     */
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
