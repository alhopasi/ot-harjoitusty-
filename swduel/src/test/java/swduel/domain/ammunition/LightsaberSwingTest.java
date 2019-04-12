package swduel.domain.ammunition;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LightsaberSwingTest {
    
    LightsaberSwing swing;
    
    @Before
    public void setUp() {
        swing = new LightsaberSwing(10, 10, 10, 40, 300, 0.07);
    }
    
    @Test
    public void lightsaberSwingCanBeCreated() {
        assertNotNull(swing);
    }
}
