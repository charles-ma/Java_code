package snake;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

    Cell cell = null;
    
    @Before
    public void setUp() {
        cell = new Cell();
    }
    
    @Test
    public void testCell() {
        //Cell cell = new Cell();
        assertTrue(cell.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(cell.isEmpty());
    }

    @Test
    public void testIsSnake() {
        assertFalse(cell.isSnake());
    }

    @Test
    public void testIsFly() {
        assertFalse(cell.isFly());
    }

    @Test
    public void testSetEmpty() {
        cell.setEmpty();
        assertTrue(cell.isEmpty());
    }
    
    @Test
    public void testSetSnake() {
        cell.setSnake();
        assertTrue(cell.isSnake());
    }

    @Test
    public void testSetfly() {
        cell.setfly();
        assertTrue(cell.isFly());
    }

}
