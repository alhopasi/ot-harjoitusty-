package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;

/**
 * Abstrakti luokka aseelle
 * 
 */
public abstract class Weapon implements Comparable<Weapon> {

    private String name;
    private int maxCooldown;
    private int cooldown;
    private int level;
    private int ammoWidth;
    private int ammoHeight;
    private int ammoVelocity;

    /**
     * Konstruktorille annetaan tiedot aseen nimestä, jäähtymisajasta, aseen tasosta sekä tiedot ammuksesta
     * @param name Aseen nimi
     * @param maxCooldown Aseen jäähtymisaika
     * @param level Aseen taso
     * @param ammoWidth Ammuksen leveys
     * @param ammoHeight Ammuksen korkeus
     * @param ammoVelocity Ammuksen nopeus
     */
    public Weapon(String name, int maxCooldown, int level, int ammoWidth, int ammoHeight, int ammoVelocity) {
        this.name = name;
        this.maxCooldown = maxCooldown;
        this.level = level;
        this.ammoWidth = ammoWidth;
        this.ammoHeight = ammoHeight;
        this.ammoVelocity = ammoVelocity;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Tämä metodi tulee ylikirjoittaa oikeassa luokassa.
     * Metodin tarkoitus on palauttaa uusi oikeanlainen ammus.
     * @param x Ammuksen vasen reuna
     * @param y Ammuksen alareuna
     * @param facing Ammuksen suunta
     * @return Palauttaa uuden oikean ammuksen.
     */
    protected Ammunition getAmmo(int x, int y, int facing) {
        System.out.println("Override getAmmo method with new weapon!");
        return null;
    }

    /**
     * Palauttaa uuden ammuksen joka lähtee oikeaan suuntaan oikealta sijainnilta.
     * @param x Ampuvan pelaajan x
     * @param y Ampuvan pelaajan y
     * @param facing Ampuvan pelaajan katsomissuunta
     * @return Palauttaa uuden ammuksen
     */
    public Ammunition getNewAmmo(int x, int y, int facing) {
        int ammoX = this.calculateAmmoX(x, ammoWidth, facing);
        int ammoY = this.calculateAmmoY(y, ammoHeight);
        int velocityX = ammoVelocity;
        if (facing == 0) {
            velocityX *= -1;
        }
        Ammunition newAmmo = getAmmo(ammoX, ammoY, velocityX);
        newAmmo.setFacing(facing);
        return newAmmo;
    }
    
    /**
     * Palauttaa aseen ääniefektin, ylikirjoita oikeassa luokassa oikealla äänellä!
     * @return oletuksena on "lasershot".
     */
    public String getSoundEffectName() {
        return "lasershot";
    }

    public int getCooldown() {
        return this.cooldown;
    }
    
    public void setCooldown(int cd) {
        this.cooldown = cd;
    }

    /**
     * Vähentää aseen jäähtymisaikaa
     * @param time Aika joka on kulunut viimeisestä kutsusta.
     */
    public void lowerCooldown(double time) {
        if (cooldown > 0) {
            this.cooldown -= time;
            if (cooldown < 0) {
                cooldown = 0;
            }
        }
    }

    public void addCooldown() {
        cooldown = maxCooldown;
    }

    public int getLevel() {
        return this.level;
    }

    /**
     * Vertaa aseiden tasoa.
     * @param other Toinen ase.
     * @return Palauttaa negatiivisen jos aseella on pienempi taso.
     */
    @Override
    public int compareTo(Weapon other) {
        int result = this.level - other.level;
        if (result == 0) {
            return this.name.compareTo(other.getName());
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private int calculateAmmoX(int x, int ammoWidth, int facing) {
        int ammoX = x;
        if (facing == 0) {
            ammoX = ammoX - ammoWidth - 10;
        }
        if (facing == 1) {
            ammoX = ammoX + 32 + 10;
        }
        return ammoX;
    }

    private int calculateAmmoY(int y, int ammoHeight) {
        int ammoY = y;
        ammoY = ammoY - (int) (64 * 0.66) + (int) ammoHeight / 2;
        return ammoY;
    }
}
