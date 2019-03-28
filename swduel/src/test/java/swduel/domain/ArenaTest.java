package swduel.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

    public class ArenaTest {
    
    Arena arena;
    
    @Before
    public void setUp() {
        arena = new Arena("smallTestArena");
    }
    
    @Test
    public void arenaIsCreated() {
        assertNotNull(arena);
    }
    
    @Test
    public void emptySpaceHasZeroWithGetTile() {
        assertEquals(0, arena.getTile(1,1));
    }

    @Test
    public void wallHasOneWithGetTile() {
        assertEquals(1, arena.getTile(0,0));
    }
    
    @Test
    public void correctWidth() {
        assertEquals(10, arena.getWidth());
    }
    
    @Test
    public void correctHeight() {
        assertEquals(6, arena.getHeight());
    }
     
}
