package swduel.domain.weapon;

import swduel.domain.ammunition.Ammunition;

public abstract class Weapon implements Comparable<Weapon> {
    
    private String name;
    private int cooldown;
    private int level;
    private Ammunition ammunition;
    
    public Weapon(String name, int cooldown, int level, Ammunition ammunition) {
        this.name = name;
        this.cooldown = cooldown;
        this.level = level;
        this.ammunition = ammunition;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCooldown() {
        return this.cooldown;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public String getAmmunitionName() {
        return this.ammunition.getName();
    }
    
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
    
}
