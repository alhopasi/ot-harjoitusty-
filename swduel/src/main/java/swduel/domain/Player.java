package swduel.domain;

import swduel.domain.weapon.Weapon;

/**
 * Luokassa on pelaajan tiedot ja siihen liittyviä metodeja
 *
 * @see swduel.domain.Sprite
 */
public class Player extends Sprite {

    private double maxVelocity;
    private Weapon weapon;
    private int id;
    private int score;
    private int runningFrame;

    /**
     * Konstruktori asettaa pelaajan kohtaan x: -1, y: -1 sekä maksiminopeuden
     * ja alustaa aloituspisteet
     *
     * @param id pelaajan id, joko 1 tai 2
     */
    public Player(int id) {
        super(-1, -1, 64, 32);
        this.id = id;
        this.maxVelocity = 300;
        this.score = 0;
        this.runningFrame = 1;
    }

    /**
     * Lisää pelaajalle pisteen.
     */
    public void addScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Asettaa pelaajalle aseen
     *
     * @param weapon ase joka asetetaan pelaajalle
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Kutsuu yläluokka Spriten updatea sekä vähentää aseen cooldownia.
     *
     * @see swduel.domain.Sprite#update(double) 
     * 
     * @param time aika jota kulunut viime päivtyksestä
     */
    @Override
    public void update(double time) {
        super.update(time);
        if (weapon != null) {
            weapon.lowerCooldown(time);
        }
    }

    /**
     * Lisätään pelaajalle nopeutta. Jos nopeus ylittää maksiminopeuden,
     * asetetaan se maksiminopeuteen
     *
     * @param x nopeuden lisäyksen määrä horisontaalisesti
     * @param y nopeuden lisäyksen määrä vertikaalisesti
     */
    @Override
    public void addVelocity(double x, double y) {
        super.addVelocity(x, y);
        if (super.getVelocityX() > maxVelocity) {
            super.setVelocity(maxVelocity, super.getVelocityY());
        }
        if (super.getVelocityX() < -maxVelocity) {
            super.setVelocity(-maxVelocity, super.getVelocityY());
        }
    }

    public double getMaxVelocity() {
        return this.maxVelocity;
    }
    
    public boolean isRunning() {
        return (super.getVelocityX() != 0 && super.getVelocityY() == 0);
    }
    
    public void setNextRunningFrame() {
        runningFrame++;
        if (runningFrame > 2) {
            runningFrame = 1;
        }
    }
    
    public int getRunningFrame() {
        return runningFrame;
    }

    @Override
    public String toString() {
        return super.toString() + "  weapon: " + weapon;
    }
}
