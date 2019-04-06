package swduel.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SpawnHandlerTest {

    SpawnHandler s;
    Player player1;
    Player player2;
    Arena arena;

    @Before
    public void setUp() {
        arena = new Arena("testArena2");
        player1 = new Player(0);
        player2 = new Player(1);
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        s = new SpawnHandler(players, arena);
    }

    @Test
    public void SpawnHandlerIsCreated() {
        assertNotNull(s);
    }

    @Test
    public void RandomPlayerLocationChangesLocation() {
        s.randomPlayerLocation(player1);
        assertNotEquals(-1, player1.getX());
        assertNotEquals(-1, player1.getY());
    }

    @Test
    public void RandomPlayerLocationGivesValidLocation() {
        s.randomPlayerLocation(player1);
        assertTrue(player1.getX() < arena.getWidth() * 32 && player1.getX() >= 0);
        assertTrue(player1.getY() < arena.getHeight() * 32 && player1.getY() >= 0);
    }

    @Test
    public void Random2ndPlayerHasHalfArenaGap() {
        s.randomPlayerLocation(player1);
        s.randomPlayerLocation(player2);
        assertTrue(Math.abs(player1.getX() - player2.getX()) >= ((arena.getWidth() - 1) / 2) * 32);
    }
    
    @Test
    public void RandomFirstAfter2ndHasHalfArenaGap() {
        s.randomPlayerLocation(player1);
        s.randomPlayerLocation(player2);
        player1.setX(-1);
        player1.setY(-1);
        s.randomPlayerLocation(player1);
        assertTrue(Math.abs(player1.getX() - player2.getX()) >= ((arena.getWidth() - 1) / 2) * 32);
    }
}
