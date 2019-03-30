package swduel.domain;

import java.util.List;
import java.util.Random;

public class SpawnHandler {

    private List<Player> players;
    private Arena arena;

    public SpawnHandler(List<Player> players, Arena arena) {
        this.players = players;
        this.arena = arena;
    }

    public void randomPlayerLocation(Player player) {
        boolean goodLocation = false;
        while (!goodLocation) {
            player.setX(-1);
            player.setY(-1);
            if (randomXLocation(player)) {
                goodLocation = randomYAndLevel(player);
            }
        }

        player.setX(player.getX() * 32);
        player.setY(player.getY() * 32 + 31);
    }

    private boolean randomXLocation(Player player) {
        int halfArenaSize = arena.getWidth() / 2 - 1;
        int newX;
        while (true) {
            newX = new Random().nextInt(halfArenaSize) + 1;
            for (Player playerToCheck : players) {
                newX = checkAndAdjustIfxIsPossible(halfArenaSize, newX, playerToCheck);
                if (newX == -1) {
                    break;
                }
            }
            if (newX != -1) {
                break;
            }
        }
        player.setX(newX);
        return true;
    }

    private boolean randomYAndLevel(Player player) {
        int randomY = new Random().nextInt(arena.getHeight() - 2) + 1;
        int checkForLoop = 0;
        while (arena.getTile(randomY, player.getX()) != 0 && arena.getTile(randomY - 1, player.getX()) != 0) {
            randomY = new Random().nextInt(arena.getHeight() - 2) + 1;
            checkForLoop++;
            if (checkForLoop == 20) {
                return false;
            }
        }
        player.setY(randomY);
        movePlayerToFloor(player);
        return true;
    }

    private void movePlayerToFloor(Player player) {
        int x = player.getX();
        int y = player.getY();
        if (arena.getTile(y + 1, x) == 0) {
            player.setY(y + 1);
            movePlayerToFloor(player);
        }
    }

    private int checkAndAdjustIfxIsPossible(int halfArenaSize, int xToCheck, Player player) {
        if (player.getX() == -1) {
            return xToCheck;
        }
        if (player.getX() / 32 <= halfArenaSize + 1) {
            xToCheck += halfArenaSize;
            if (xToCheck < player.getX() / 32 + halfArenaSize) {
                return -1;
            }
        } else {
            if (xToCheck > player.getX() / 32 - halfArenaSize) {
                return -1;
            }
        }
        return xToCheck;
    }
}
