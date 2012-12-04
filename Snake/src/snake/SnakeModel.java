package snake;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeModel extends Observable {

    public Cell[][] grid;
    public final static int right = 0, down = 1, left = 2, up = 3;
    private Cell head = new Cell();
    private Cell tail = new Cell();
    private ArrayList<Cell> flies = new ArrayList<Cell>();
    private int lengthSnake = 5;
    private int lengthIncrement = 0;
    private int increCount = 0;
    private int maxRows, maxCols;
    private int speed = 1;
    private int dir = SnakeModel.up;
    private int frequency = 41;
    private int picCount = 0;
    private int maxFlies = 5;
    private int minFlies = 1;
    private int maxDuration = 50;
    private int minDuration = 10;
    private boolean isOver = false;

    /**
     * @return the isOver
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * @param isOver the isOver to set
     */
    public void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    private Timer timer = new Timer();

    /**
     * Constructor of the SnakeModel
     */
    public SnakeModel(int maxRows, int maxCols, int lengthSnake) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.grid = new Cell[maxRows][maxCols];
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
        initSnake(lengthSnake);
        // !!!
        this.timer.schedule(new UpdateStatus(this), 1000, this.frequency);
        this.picCount = this.frequency / this.speed;
    }

    /**
     * Initialize the snake
     * 
     * @param initialLength
     *            is an int.
     */
    public void initSnake(int initialLength) {
        this.head = this.grid[this.maxRows - 1][this.maxCols / 2];
        this.head.setSnake();
        this.lengthSnake = initialLength;
    }

    /**
     * Move the snake. This function will be called every time period.
     * 
     * @return a boolean value
     */
    public boolean move() {
        Cell nextHead = null;
        if (this.dir == SnakeModel.up) {
            if (this.head.getX() <= 0)
                return false;
            nextHead = this.grid[this.head.getX() - 1][this.head.getY()];
            if (!nextHead.isFly())
                this.increCount += this.lengthIncrement;
            updateTail();
            if (nextHead.isSnake())
                return false;
            updateHead(nextHead);
        } else if (this.dir == SnakeModel.down) {
            if (this.head.getX() >= this.maxRows - 1)
                return false;
            nextHead = this.grid[this.head.getX() + 1][this.head.getY()];
            if (!nextHead.isFly())
                this.increCount += this.lengthIncrement;
            updateTail();
            if (nextHead.isSnake())
                return false;
            updateHead(nextHead);
        } else if (this.dir == SnakeModel.right) {
            if (this.head.getY() >= this.maxCols - 1)
                return false;
            nextHead = this.grid[this.head.getX()][this.head.getY() + 1];
            if (!nextHead.isFly())
                this.increCount += this.lengthIncrement;
            updateTail();
            if (nextHead.isSnake())
                return false;
            updateHead(nextHead);
        } else if (this.dir == SnakeModel.left) {
            if (this.head.getY() <= 0)
                return false;
            nextHead = this.grid[this.head.getX()][this.head.getY() - 1];
            if (!nextHead.isFly())
                this.increCount += this.lengthIncrement;
            updateTail();
            if (nextHead.isSnake())
                return false;
            updateHead(nextHead);
        }
        return true;
    }

    /**
     * Update the head of the snake.
     * 
     * @param nextHead
     *            is a Cell.
     */
    public void updateHead(Cell nextHead) {
        this.head.setNext(nextHead);
        this.head = nextHead;
        this.head.setSnake();
    }

    /**
     * Update the tail of the snake.
     */
    public void updateTail() {
        if (this.increCount == 0) {
            Cell lastTail = this.tail;
            if (this.tail.getNext() != null)
                this.tail = this.tail.getNext();
            lastTail.setEmpty();
        } else {
            this.increCount--;
            this.lengthSnake++;
        }

    }

    /**
     * Update all the cells in the picture.
     * 
     * @return a boolean value.
     */
    public boolean update() {
        if (!move()) {
            this.timer.cancel();
            this.isOver = true;
            return false;
        }
        geneFlies();
        return true;
    }

    /**
     * Generate random flies every time period.
     */
    public void geneFlies() {
        ArrayList<Cell> newFlies = new ArrayList<Cell>();
        for (Cell c : this.flies) {
            c.setDuration(c.getDuration() - 1);
            if (c.getDuration() <= 0 && c.isFly())
                c.setEmpty();
            if (c.isFly())
                newFlies.add(c);
        }
        this.flies = newFlies;
        Random random = new Random();
        int flyNum = random.nextInt(this.maxFlies) + 1;
        for (int i = 0; i < flyNum; ++i) {
            int x = 0, y = 0;
            do {
                x = random.nextInt(this.maxRows);
                y = random.nextInt(this.maxCols);
            } while (!this.grid[x][y].isEmpty());
            this.flies.add(this.grid[x][y]);
            this.grid[x][y].setfly();
            this.grid[x][y].setDuration(random.nextInt(this.maxDuration
                    - this.minDuration + 1)
                    + this.minDuration);
        }
    }

    /**
     * Turn right relatively.
     */
    public void turnReRight() {
        this.dir = (this.dir + 1) % 4;
    }

    /**
     * Turn left relatively.
     */
    public void turnReLeft() {
        this.dir = (this.dir + 3) % 4;
    }

    /**
     * Turn right absolutely.
     */
    public void turnAbRight() {
        this.dir = SnakeModel.right;
    }

    /**
     * Turn left absolutely.
     */
    public void turnAbLeft() {
        this.dir = SnakeModel.left;
    }

    /**
     * Turn up absolutely.
     */
    public void turnAbUp() {
        this.dir = SnakeModel.up;
    }

    /**
     * Turn down absolutely.
     */
    public void turnAbDown() {
        this.dir = SnakeModel.down;
    }

    /**
     * Set the speed of the snake.
     * 
     * @param speed
     *            is an int value.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
        this.picCount = this.frequency / this.speed;
    }

    /**
     * Get the speed of the snake.
     * 
     * @return the speed of the snake.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @return the maxFlies
     */
    public int getMaxFlies() {
        return maxFlies;
    }

    /**
     * @param maxFlies
     *            the maxFlies to set
     */
    public void setMaxFlies(int maxFlies) {
        this.maxFlies = maxFlies;
    }

    /**
     * @return the maxDuration
     */
    public int getMaxDuration() {
        return maxDuration;
    }

    /**
     * @param maxDuration
     *            the maxDuration to set
     */
    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    /**
     * @return the minFlies
     */
    public int getMinFlies() {
        return minFlies;
    }

    /**
     * @param minFlies
     *            the minFlies to set
     */
    public void setMinFlies(int minFlies) {
        this.minFlies = minFlies;
    }

    /**
     * @return the minDuration
     */
    public int getMinDuration() {
        return minDuration;
    }

    /**
     * @param minDuration
     *            the minDuration to set
     */
    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    /**
     * Class to run the timer task.
     * 
     * @author machao
     * 
     */
    private class UpdateStatus extends TimerTask {

        SnakeModel model;
        int count = 0;
        int initCount = 0;

        /**
         * Constructor of the timer task.
         * 
         * @param model
         *            is a SnakeModel.
         */
        public UpdateStatus(SnakeModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            count++;
            if (count >= this.model.picCount) {
                count = 0;
                this.model.update();
                initCount++;
                if (initCount == this.model.lengthSnake - 1)
                    this.model.tail = this.model.grid[this.model.maxRows - 1][this.model.maxCols / 2];
            }
            this.model.setChanged();
            this.model.notifyObservers();
        }
    }

    /**
     * Get the maxRows.
     * 
     * @return an int value.
     */
    public int getMaxRow() {
        return this.maxRows;
    }

    /**
     * Set the maxRows.
     * 
     * @param maxRows
     *            is an int value.
     */
    public void setMaxRow(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Get the maxCols.
     * 
     * @return an int value.
     */
    public int getMaxCol() {
        return this.maxCols;
    }

    /**
     * Set the maxCols.
     * 
     * @param maxCol
     *            is an int value.
     */
    public void setMaxCol(int maxCol) {
        this.maxCols = maxCol;
    }

    /**
     * Get the number of time period for each step of the snake.
     * 
     * @return an int value.
     */
    public int getPicCount() {
        return this.picCount;
    }

    /**
     * Get the length of the snake.
     * 
     * @return an int value.
     */
    public int getSnakeL() {
        return this.lengthSnake;
    }

    /**
     * Set the length of the snake.
     * 
     * @param lengthSnake
     *            is an int value.
     */
    public void setSnakeL(int lengthSnake) {
        this.lengthSnake = lengthSnake;
    }

    /**
     * Get the head of the snake.
     * @return a cell.
     */
    public Cell getHead() {
        return this.head;
    }

    /**
     * Get the tail of the snake.
     * @return a cell.
     */
    public Cell getTail() {
        return this.tail;
    }

    /**
     * Get all the cells.
     * @return an array of cells.
     */
    public Cell[][] getGrid() {
        return this.grid;
    }

    /**
     * @return the flies
     */
    public ArrayList<Cell> getFlies() {
        return flies;
    }

    /**
     * @param flies
     *            the flies to set
     */
    public void setFlies(ArrayList<Cell> flies) {
        this.flies = flies;
    }

    /**
     * @return the lengthSnake
     */
    public int getLengthSnake() {
        return lengthSnake;
    }

    /**
     * @param lengthSnake
     *            the lengthSnake to set
     */
    public void setLengthSnake(int lengthSnake) {
        this.lengthSnake = lengthSnake;
    }

    /**
     * @return the lengthIncrement
     */
    public int getLengthIncrement() {
        return lengthIncrement;
    }

    /**
     * @param lengthIncrement
     *            the lengthIncrement to set
     */
    public void setLengthIncrement(int lengthIncrement) {
        this.lengthIncrement = lengthIncrement;
    }

    /**
     * @return the increCount
     */
    public int getIncreCount() {
        return increCount;
    }

    /**
     * @param increCount
     *            the increCount to set
     */
    public void setIncreCount(int increCount) {
        this.increCount = increCount;
    }

    /**
     * @return the maxRows
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * @param maxRows
     *            the maxRows to set
     */
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * @return the maxCols
     */
    public int getMaxCols() {
        return maxCols;
    }

    /**
     * @param maxCols
     *            the maxCols to set
     */
    public void setMaxCols(int maxCols) {
        this.maxCols = maxCols;
    }

    /**
     * @return the dir
     */
    public int getDir() {
        return dir;
    }

    /**
     * @param dir
     *            the dir to set
     */
    public void setDir(int dir) {
        this.dir = dir;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency
     *            the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @param timer
     *            the timer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * @return the right
     */
    public static int getRight() {
        return right;
    }

    /**
     * @return the down
     */
    public static int getDown() {
        return down;
    }

    /**
     * @return the left
     */
    public static int getLeft() {
        return left;
    }

    /**
     * @return the up
     */
    public static int getUp() {
        return up;
    }

    /**
     * @param grid
     *            the grid to set
     */
    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    /**
     * @param head
     *            the head to set
     */
    public void setHead(Cell head) {
        this.head = head;
    }

    /**
     * @param tail
     *            the tail to set
     */
    public void setTail(Cell tail) {
        this.tail = tail;
    }

    /**
     * @param picCount
     *            the picCount to set
     */
    public void setPicCount(int picCount) {
        this.picCount = picCount;
    }
}
