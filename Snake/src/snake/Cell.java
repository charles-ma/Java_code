package snake;

/**
 * @author Charles(Chao) Ma
 * @author Karan Srivastava
 * @version Dec 5, 2012
 */
public class Cell {
    public enum state{
        EMPTY, SNAKE, FLY
    }
    
    private state cellState;
    private int x = -1, y = -1;
    private Cell next = null;//used to record the last element of the snake
    private int duration = 0;
    
    /**
     * Constructor. Initialize state to empty
     */
    public Cell() {
        cellState = state.EMPTY;
    }
    
    /**
     * @param int x
     * @param int y
     *          Constructor. Initialize state to empty and set x,y
     */
    public Cell(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return boolean
     *             true if empty
     */
    public boolean isEmpty() {
        return cellState == state.EMPTY;
    }
    
    /**
     * @return boolean
     *             true if snake
     */
    public boolean isSnake() {
        return cellState == state.SNAKE;
    }

    /**
     * @return boolean
     *             true if fly
     */
    public boolean isFly() {
        return cellState == state.FLY;
    }

    /**
     * Set cell to empty
     */
    public void setEmpty() {
        cellState = state.EMPTY;
        next = null;
    }

    /**
     * Set cell to snake
     */
    public void setSnake() {
        cellState = state.SNAKE;
    }

    /**
     * Set cell to fly
     */
    public void setfly() {
        cellState = state.FLY;
        next = null;
    }

    /**
     * @return int
     *          x value of the cell
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * @return int
     *          y value of the cell
     */
    public int getY() {
        return this.y;
    }
    

    /**
     * @param x
     *        set the x value
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @param y
     *        set the y value
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * @param next
     *          set the next cell
     */
    public void setNext(Cell next) {
        this.next = next;
    }
    
    /**
     * @return Cell
     *           the next cell
     */
    public Cell getNext() {
        return this.next;
    }
    
    /**
     * @param duration
     *           the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    /**
     * @return int
     *          the duration
     */
    public int getDuration() {
        return this.duration;
    }
}
