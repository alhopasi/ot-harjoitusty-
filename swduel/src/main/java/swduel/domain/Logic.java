package swduel.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import swduel.domain.ammunition.Ammunition;
import swduel.domain.weapon.Blaster;
import swduel.domain.weapon.BlasterRifle;
import swduel.domain.weapon.Lightsaber;
import swduel.domain.weapon.Weapon;

public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    private WallCollisionHandler wallCollisionHandler;
    private TreeSet<Weapon> weapons;
    private List<Ammunition> shotsFired;
    private boolean gameFinished;

    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        wallCollisionHandler = new WallCollisionHandler(arena);

        weapons = generateWeapons();

        players.add(new Player(0));
        players.add(new Player(1));
        players.get(0).setFacing(1);
        for (Player player : players) {
            spawner.randomPlayerLocation(player);
            setWeapon(player, weapons.first().getName());
        }

        shotsFired = new ArrayList<>();
    }

    public Arena getArena() {
        return this.arena;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void updateAll(double time) {
        for (Player player : players) {
            player.update(time);
            slowDown(player);
            wallCollisionHandler.checkIfInsideWall(player);
            gravity(player);
        }
        updateAmmoPosition(time);
    }

    public void attack(Player player) {
        if (player.getWeapon().getCooldown() > 0) {
            return;
        }
        createAmmunition(player);
        player.getWeapon().addCooldown();
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public List<Ammunition> getAmmunition() {
        return this.shotsFired;
    }

    private void updateAmmoPosition(double time) {
        Iterator<Ammunition> it = shotsFired.iterator();
        while (it.hasNext()) {
            Ammunition ammo = it.next();
            ammo.update(time);
            checkArenaBoundaries(it, ammo);
            if (ammo.getAliveTime() < 0) {
                it.remove();
                continue;
            }
            checkPlayerHits(it, ammo);
        }
    }

    private void checkArenaBoundaries(Iterator it, Ammunition ammo) {
        if (ammo.getX() < 0
                || ammo.getX() + ammo.getWidth() >= arena.getWidth() * 32
                || ammo.getY() - ammo.getHeight() < 0
                || ammo.getY() >= arena.getHeight() * 32) {
            it.remove();
        }
    }

    private void checkPlayerHits(Iterator it, Ammunition ammo) {
        for (Player player : players) {
            if (ammo.collides(player)) {
                handlePlayerHit(player);
                it.remove();
            }
        }
    }

    private void handlePlayerHit(Player player) {
        int winnerId;
        if (player.getId() == 0) {
            winnerId = 1;
        } else {
            winnerId = 0;
        }
        Weapon nextWeapon = weapons.higher(players.get(winnerId).getWeapon());

        if (nextWeapon == null) {
            System.out.println("PELI LOPPUI - PELAAJA " + (winnerId + 1) + " VOITTI!");
            gameFinished = true;
            return;
        }
        
        setWeapon(players.get(winnerId), nextWeapon.getName());
        player.setX(-1);
        player.setY(-1);
        spawner.randomPlayerLocation(player);
    }
    
    private void setWeapon(Player player, String weapon) {
        Weapon newWeapon;
        if (weapon.equals("Blaster")) {
            newWeapon = new Blaster();
        } else if (weapon.equals("Blaster Rifle")) {
            newWeapon = new BlasterRifle();
        } else {
            newWeapon = new Lightsaber();
        }
        player.setWeapon(newWeapon);
    }

    private void createAmmunition(Player player) {
        int facing = player.getFacing();
        int x = player.getX();
        int y = player.getY();
        
        Ammunition newAmmo = player.getWeapon().getNewAmmo(x, y, facing);
        shotsFired.add(newAmmo);
    }

    private void slowDown(Player player) {
        double velocityX = player.getVelocityX();
        double velocityY = player.getVelocityY();
        if (velocityX >= -15 && velocityX <= 15) {
            player.setVelocity(0, velocityY);
        }
        if (velocityX < -15) {
            player.addVelocity(15, 0);
        }
        if (velocityX > 15) {
            player.addVelocity(-15, 0);
        }
    }

    private void gravity(Player player) {
        double velocityX = player.getVelocityX();
        double velocityY = player.getVelocityY();
        int playerY = (player.getY() + 1) / 32;
        int playerXsw = player.getX() / 32;
        int playerXse = (player.getX() + 31) / 32;
        if (arena.getTile(playerY, playerXsw) != 0 || arena.getTile(playerY, playerXse) != 0) {
            player.setVelocity(velocityX, 0);
        } else {
            player.addVelocity(0, 15);
        }
    }

    private TreeSet<Weapon> generateWeapons() {
        TreeSet<Weapon> newWeapons = new TreeSet<>();
        newWeapons.add(new BlasterRifle());
        newWeapons.add(new Blaster());
        newWeapons.add(new Lightsaber());
        return newWeapons;
    }
}
