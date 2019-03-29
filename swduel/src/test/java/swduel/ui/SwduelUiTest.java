package swduel.ui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwduelUiTest {
    
    SwduelUi ui;
    
    @Before
    public void setUp() {
        ui = new SwduelUi();
    }
    
    @Test
    public void swduelUiCanBeCreated() {
        assertNotNull(ui);
    }
    
}
