package swduel.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import swduel.domain.weapon.Blaster;
import swduel.domain.weapon.Lightsaber;
import swduel.domain.weapon.Weapon;

public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    private WallCollisionHandler wallCollisionHandler;
    TreeSet<Weapon> weapons;

    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        wallCollisionHandler = new WallCollisionHandler(arena);

        weapons = generateWeapons();

        players.add(new Player());
        players.add(new Player());
        players.get(0).setFacing(1);
        for (Player player : players) {
            spawner.randomPlayerLocation(player);
            player.setWeapon(weapons.first());
        }
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
        newWeapons.add(new Blaster());
        newWeapons.add(new Lightsaber());
        return newWeapons;
    }
}
