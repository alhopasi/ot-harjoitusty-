package swduel.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwduelUiTest {
    
    SwduelUi ui;
    
    @Before
    public void setUp() {
        ui = new SwduelUi();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void swduelUiCanBeCreated() {
        assertNotNull(ui);
    }
}
