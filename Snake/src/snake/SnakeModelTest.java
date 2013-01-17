package snake;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SnakeModelTest {

    public SnakeModel model;
    
    @Before
    public void setUp() {
        this.model = new SnakeModel(50, 50, 5);
    }
    
    @Test
    public void testSnakeModel() {
        assertEquals(this.model.getMaxRow(), 50);
        assertEquals(this.model.getMaxCol(), 50);
        assertEquals(this.model.getPicCount(), 6);
        assertEquals(this.model.getSnakeL(), 5);
    }

    @Test
    public void testInitSnake() {
        assertEquals(this.model.getHead().getX(), 49);
        assertEquals(this.model.getHead().getY(), 25);
        assertEquals(this.model.getGrid()[49][25], this.model.getHead());
        assertTrue(this.model.getHead().isSnake());
        assertEquals(this.model.getDir(), SnakeModel.up);
    }

    @Test
    public void testMove() {
        this.model.move();
        assertEquals(this.model.getHead().getX(), 48);
        assertTrue(this.model.getHead().isSnake());
        assertTrue(this.model.getGrid()[48][25].isSnake());
        assertEquals(this.model.getIncreCount(), 0);
        assertTrue(this.model.getTail().isEmpty());
        this.model.setDir(SnakeModel.down);
        assertFalse(this.model.move());
    }

    @Test
    public void testUpdate() {
        this.model.move();
        this.model.turnAbDown();
        assertFalse(this.model.update());
    }

    @Test
    public void testGeneFlies() {
        this.model.geneFlies();
        assertFalse(this.model.getFlies().isEmpty());
        assertTrue(this.model.getFlies().get(0).isFly());
        assertTrue(this.model.getFlies().get(0).getDuration() < 20 && this.model.getFlies().get(0).getDuration() > 5);
    }

    @Test
    public void testTurnReRight() {
        this.model.turnReRight();
        assertEquals(this.model.getDir(), SnakeModel.right);
        this.model.turnReRight();
        assertEquals(this.model.getDir(), SnakeModel.down);
    }

    @Test
    public void testTurnReLeft() {
        this.model.turnReLeft();
        assertEquals(this.model.getDir(), SnakeModel.left);
        this.model.turnReLeft();
        assertEquals(this.model.getDir(), SnakeModel.down);
    }

    @Test
    public void testTurnAbRight() {
        this.model.turnAbRight();
        assertEquals(this.model.getDir(), SnakeModel.right);
    }

    @Test
    public void testTurnAbLeft() {
        this.model.turnAbLeft();
        assertEquals(this.model.getDir(), SnakeModel.left);
    }

    @Test
    public void testTurnAbUp() {
        this.model.turnAbUp();
        assertEquals(this.model.getDir(), SnakeModel.up);
    }

    @Test
    public void testTurnAbDown() {
        this.model.turnAbDown();
        assertEquals(this.model.getDir(), SnakeModel.down);
    }

    @Test
    public void testSetSpeed() {
        this.model.setSpeed(3);
        assertEquals(this.model.getSpeed(), 3);
        assertEquals(this.model.getPicCount(), 13);
    }

  }
