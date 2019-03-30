package swduel.domain;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    private WallCollisionHandler wallCollisionHandler;

    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        wallCollisionHandler = new WallCollisionHandler(arena);

        players.add(new Player());
        players.add(new Player());
        players.get(0).setFacing(1);
        for (Player player : players) {
            spawner.randomPlayerLocation(player);
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
        if (velocityX >= -10 && velocityX <= 10) {
            player.setVelocity(0, velocityY);
        }
        if (velocityX < -10) {
            player.addVelocity(15, 0);
        }
        if (velocityX > 10) {
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
}
