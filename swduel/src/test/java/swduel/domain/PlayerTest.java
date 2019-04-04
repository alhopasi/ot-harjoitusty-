package swduel.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void playerIsCreated() {
        assertNotNull(player);
    }

    @Test
    public void velocityCanBeSetAndGet() {
        player.setVelocity(20, 10);
        assertEquals(20, player.getVelocityX(), 0);
        assertEquals(10, player.getVelocityY(), 0);
    }
    
    @Test
    public void velocityCanBeAdded() {
        player.addVelocity(10, 30);
        player.addVelocity(5.5, 20.5);
        
        assertEquals(15.5, player.getVelocityX(), 0.001);
        assertEquals(50.5, player.getVelocityY(), 0.001);
    }
    
    @Test
    public void addingVelocityXStopsAtMaxVelocity() {
        player.addVelocity(500, 0);
        assertEquals(player.getMaxVelocity(), player.getVelocityX(), 0);
        player.addVelocity(-1000, 0);
        assertEquals(-player.getMaxVelocity(), player.getVelocityX(), 0);
    }
    
    @Test
    public void facingCanBeSetAndGet() {
        player.setFacing(0);
        assertEquals(0, player.getFacing());
        player.setFacing(1);
        assertEquals(1, player.getFacing());
    }
    
    @Test
    public void playerPositionCanBeSetAndGet() {
        player.setX(64);
        player.setY(40);
        assertEquals(64, player.getX());
        assertEquals(40, player.getY());
    }
    
    @Test
    public void playerDimensionsCanBeGet() {
        assertEquals(64, player.getHeight());
        assertEquals(32, player.getWidth());
    }
    
    @Test
    public void positionCanBeUpdated() {
        player.setX(0);
        player.setY(0);
        player.setVelocity(60, 10);
        player.update(0.5);
        assertEquals(30, player.getX());
        assertEquals(5, player.getY());
    }
    
    @Test
    public void playerStringPrints() {
        player.setX(0);
        player.setY(0);
        assertEquals("(0:0)", player.toString());
    }
    
    
    
}
