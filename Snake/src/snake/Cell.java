package snake;

public class Cell {
    public enum state{
        EMPTY, SNAKE, FLY
    }
    
    private state cellState;
    private int x = -1, y = -1;
    private Cell next = null;//used to record the last element of the snake
    private int duration = 0;
    
    public Cell() {
        cellState = state.EMPTY;
    }
    
    public Cell(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public boolean isEmpty() {
        return cellState == state.EMPTY;
    }
    public boolean isSnake() {
        return cellState == state.SNAKE;
    }
    public boolean isFly() {
        return cellState == state.FLY;
    }
    
    public void setEmpty() {
        cellState = state.EMPTY;
        next = null;
    }
    public void setSnake() {
        cellState = state.SNAKE;
    }
    public void setfly() {
        cellState = state.FLY;
        next = null;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setNext(Cell next) {
        this.next = next;
    }
    public Cell getNext() {
        return this.next;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return this.duration;
    }
}
