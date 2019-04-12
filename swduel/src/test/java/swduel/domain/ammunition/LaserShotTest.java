package swduel.domain.ammunition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class LaserShotTest {

    LaserShot shot;

    @Before
    public void setUp() {
        shot = new LaserShot(10, 10, 16, 5, 100, 2);
    }

    @Test
    public void laserShotCanBeCreated() {
        assertNotNull(shot);
    }

    @Test
    public void ammunitionFilenameEquals() {
        assertEquals("lasershot", shot.getFileName());
    }

    @Test
    public void ammunitionGetAliveTimeEquals() {
        assertEquals(2, shot.getAliveTime(), 0);
    }

    @Test
    public void ammunitionUpdateWorks() {
        shot.update(1);
        assertEquals(110, shot.getX());
        assertEquals(1, shot.getAliveTime(), 0);
    }
    
    @Test
    public void ammunitionToStringWorks() {
        assertEquals("(10.0:10.0)  100.0:0.0  lasershot  2.0", shot.toString());
    }
}
