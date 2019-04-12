package swduel.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

    public class ArenaTest {
    
    Arena arena;
    
    @Before
    public void setUp() {
        arena = new Arena("testArena");
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
        assertEquals(32, arena.getWidth());
    }
    
    @Test
    public void correctHeight() {
        assertEquals(24, arena.getHeight());
    }
    
    @Test
    public void getTileOutsideAreaReturnsNegative() {
        assertEquals(-1, arena.getTile(-2, 2));
        assertEquals(-1, arena.getTile(2, -2));
        assertEquals(-1, arena.getTile(2000, 2));
        assertEquals(-1, arena.getTile(2, 2000));
    }
     
}
