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
        int halfArenaSize = arena.getWidth() / 2 - 1;
        int newX;
        while (true) {
            newX = new Random().nextInt(halfArenaSize) + 1;
            for (Player playerToCheck : players) {
                newX = checkIfXIsValid(halfArenaSize, newX, playerToCheck);
                if (newX == -1) {
                    break;
                }
            }
            if (newX != -1) {
                break;
            }
        }
        int newY = randomYAndLevel(newX);
        player.setX(newX * 16);
        player.setY(newY * 16);
    }

    private int randomYAndLevel(int x) {
        int randomY = new Random().nextInt(arena.getHeight() - 2) + 1;
        while (arena.getTile(randomY, x) != 0) {
            randomY = new Random().nextInt(arena.getHeight() - 2) + 1;
        }
        return checkTileBelow(randomY, x);
    }

    private int checkTileBelow(int y, int x) {
        if (arena.getTile(y, x) != 0) {
            return y - 1;
        }
        return (checkTileBelow(y + 1, x));
    }

    private int checkIfXIsValid(int halfArenaSize, int xToCheck, Player player) {
        if (player.getX() == -1) {
            return xToCheck;
        }
        if (player.getX() / 16 <= halfArenaSize + 1) {
            xToCheck += halfArenaSize;
            if (xToCheck < player.getX() / 16 + halfArenaSize) {
                return -1;
            }
        } else {
            if (xToCheck > player.getX() / 16 - halfArenaSize) {
                return -1;
            }
        }
        return xToCheck;
    }
}
