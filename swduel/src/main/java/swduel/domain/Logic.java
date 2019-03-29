package swduel.domain;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    
    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        
        players.add(new Player());
        players.add(new Player());
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
    
    public void movePlayer(Player player, String direction) {
        int newX = 0;
        if (direction.equals("left")) {
            newX = -5;
        } else {
            newX = 5;
        }
        player.setX(player.getX() + newX);
    }
}
