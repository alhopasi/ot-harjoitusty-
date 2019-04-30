package swduel.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SmokeTest {

    Smoke smoke;

    @Before
    public void setUp() {
        smoke = new Smoke(50, 50);
    }

    @Test
    public void smokeIsCreated() {
        assertNotNull(smoke);
    }

    @Test
    public void smokeMovesDownwards() {
        smoke.update(1);
        assertEquals(200, smoke.getY());
    }
}
