package swduel.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    
    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        
        players.add(new Player(Color.GREEN));
        players.add(new Player(Color.RED));
        for (Player player : players) {
            spawner.randomPlayerLocation(player);
        }
    }

    public Arena getArena() {
        return this.arena;
    }
}
