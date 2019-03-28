package swduel.utils;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    public UtilsTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void utilsCanBeCreated() {
        // why would you create it, it's static class, duh!
        Utils utils = new Utils();
        assertNotNull(utils);
    }

    @Test
    public void readFileGivesListWithText() {
        List<String> text = Utils.readFile("arenas/smallTestArena");
        assertFalse(text.isEmpty());
    }

    @Test
    public void readingFileGivesEmptyListWhenFileNotFound() {
        List<String> text = Utils.readFile("thisFileDoesNotExist");
        assertTrue(text.isEmpty());
    }
}
