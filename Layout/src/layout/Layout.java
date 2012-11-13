package layout;

//Author Chao Ma
public class Layout {
    int [][] array;
    /**
     * constructor of this class
     * @param array is a one-dimensional array
     */
    public Layout(int [] array) {
        this.array = new int[1][array.length];
        for(int i = 0; i < array.length; ++ i) {
            this.array[0][i] = array[i];
        }
    }
    
    /**
     * another constructor of this class
     * @param array is a two-dimensional array
     */
    public Layout(int [][] array) {
        if(array.length == 0) {
            this.array = new int[0][0];
        } else {
            this.array = new int[array.length][array[0].length];
        }
        for(int i = 0; i < array.length; ++ i) {
            for(int j = 0; j < array[i].length; ++ j) {
                this.array[i][j] = array[i][j];
            }
        }
    }
    
    /**
     * a third constructor of this class
     * initialize the class with an array containing #1 to length
     * @param length indicates the length of the array
     */
    public Layout(int length) {
        this.array = new int[1][length];
        for(int i = 0; i < length; ++ i) {
            this.array[0][i] = i + 1;
        }
    }
    
    /**
     * Returns a new Layout whose values are in the reverse order of those in the given Layout. 
     * @return new layout
     */
    public Layout reverse() {
        int[][] reArray = new int[this.array.length][this.array[0].length];
        for(int i = 0; i < this.array.length; ++ i) {
            for(int j = 0; j < this.array[0].length; ++ j) {
                reArray[i][this.array[0].length - 1 - j] = this.array[i][j]; 
            }
        }
        if(reArray.length == 1) 
            return new Layout(reArray[0]);
        return new Layout(reArray);
    }
    
    /**
     * Returns a new Layout which is "rotated" a quarter-turn clockwise. 
     * @return new layout
     */
    public Layout rotateRight() {
        int [][] rRoArray = new int[this.array[0].length][this.array.length];
        for(int i = 0; i < this.array.length; ++ i) {
            for(int j = 0; j < this.array[0].length; ++ j) {
                rRoArray[j][this.array.length - 1 - i] = this.array[i][j]; 
            }
        }
        if(rRoArray.length == 1) 
            return new Layout(rRoArray[0]);
        return new Layout(rRoArray);
    }
    
    /**
     * Returns a new Layout which is "rotated" a quarter-turn counterclockwise. 
     * @return new layout
     */
    public Layout rotateLeft() {
        int [][] rLeArray = new int[this.array[0].length][this.array.length];
        for(int i = 0; i < this.array.length; ++ i) {
            for(int j = 0; j < this.array[0].length; ++ j) {
                rLeArray[this.array[0].length - 1 - j][i] = this.array[i][j]; 
            }
        }
        if(rLeArray.length == 1) 
            return new Layout(rLeArray[0]);
        return new Layout(rLeArray);
    }
    
    /**
     * Transposes a Layout of m rows and n columns to form a Layout of n rows and m columns.
     * @return new layout
     */
    public Layout transpose() {
        int [][] transArray = new int[this.array[0].length][this.array.length];
        for(int i = 0; i < this.array.length; ++ i) {
            for(int j = 0; j < this.array[0].length; ++ j) {
                transArray[j][i] = this.array[i][j]; 
            }
        }
        if(transArray.length == 1) 
            return new Layout(transArray[0]);
        return new Layout(transArray);
    }
    
    /**
     * Takes a one-dimensional Layout of m * n numbers and returns a two-dimensional Layout of m 
     * rows and n columns. The first n numbers of the given Layout are copied into the first row 
     * of the new Layout, the second n numbers into the second row, and so on. This method throws 
     * an IllegalArgumentException if the length of the input Layout is not evenly divisible by n. 
     * @param n is the return column of the layout
     * @return new layout
     */
    public Layout ravel(int n) {
        int[][] raArray;
        if(this.array.length > 1) 
            throw new UnsupportedOperationException("This method cannot be applied to multi-dimensional layout.");
        else if(this.array[0].length % n != 0)
            throw new IllegalArgumentException("The length of the layout cannot be divided evenly by this argument!");
        else {
            int rowNum = this.array[0].length / n;
            raArray = new int[rowNum][n];
            for(int i = 0; i < this.array[0].length; ++ i) {
                raArray[i / n][i % n] = this.array[0][i];
            }
        }
        return new Layout(raArray);
    }
    
    /**
     * Takes a m by n two dimensional Layout and returns a one-dimensional Layout of size m � n 
     * containing the same numbers. The first n numbers of the new Layout are copied from the first 
     * row of the given Layout, the second n numbers from the second row, and so on. 
     * @return new layout
     */
    public Layout unravel() {
        int[][] unRaArray;
        int len = this.array[0].length * this.array.length;
        unRaArray = new int[1][len];
        for(int i = 0; i < this.array.length; ++ i) {
            for(int j = 0; j < this.array[0].length; ++ j) {
                unRaArray[0][i * this.array[0].length + j] = this.array[i][j];
            }
        }
        return new Layout(unRaArray);
    }
    
    /**
     * Takes a two-dimensional array of r rows and c columns and reshapes it to have n columns by 
     * (r*c)/n rows. This method throws an IllegalArgumentException if r*c is not evenly divisible by n. 
     * @param n is the column count of the return layout
     * @return new layout
     */
    public Layout reshape(int n) {
        Layout oneD = this.unravel();
        Layout reshapedLayout = oneD.ravel(n);
        return reshapedLayout;
    }
    
    /**
     * Adjoins a Layout with n rows and m1 columns to the parameter Layout with n rows and m2 columns, 
     * forming a new Layout with n rows and m1+m2 columns. This method throws an IllegalArgumentException 
     * if the input Layouts do not have the same number of rows.
     * @param layout to be joined with
     * @return new layout
     */
    public Layout join(Layout layout) {
        int[][] joinArray = null;
        int[][] otherArray = null; 
        if(this.rowCount() != layout.rowCount())
            throw new IllegalArgumentException("The layouts should have the same row number!");
        else {
            otherArray = layout.toArray2D();
            joinArray = new int[this.array.length][this.array[0].length + otherArray[0].length];
            for(int i = 0; i < this.array.length; ++ i) {
                for(int j = 0; j < this.array[0].length; ++ j)
                    joinArray[i][j] = this.array[i][j];
                for(int k = 0; k < otherArray[0].length; ++ k)
                    joinArray[i][this.array[0].length + k] = otherArray[i][k];
            }
        }
        return new Layout(joinArray);
    }
    
    /**
     * Forms a new Layout with n rows and m1+m2 columns by putting the recipient Layout with n1 rows and m 
     * columns on top of the parameter Layout of n2 rows and m columns. This method throws an IllegalArgumentException 
     * if the input Layouts do not have the same number of columns. 
     * @param layout is the layout to be stacked with
     * @return a new layout
     */
    public Layout stack(Layout layout) {
        int[][] stackArray = null;
        int[][] otherArray = null; 
        if(this.columnCount() != layout.columnCount())
            throw new IllegalArgumentException("The layouts should have the same column number!");
        else {
            otherArray = layout.toArray2D();
            stackArray = new int[this.array.length + otherArray.length][this.array[0].length];
            for(int i = 0; i < this.array[0].length; ++ i) {
                for(int j = 0; j < this.array.length; ++ j)
                    stackArray[j][i] = this.array[j][i];
                for(int k = 0; k < otherArray.length; ++ k)
                    stackArray[this.array.length + k][i] = otherArray[k][i];
            }
        }
        return new Layout(stackArray);
    }
    
    /**
     * Returns the number of rows in the Layout. For a one-dimensional Layout, this returns 1.
     * @return the number of the rows
     */
    public int rowCount() {
        return this.array.length;
    }
    
    /**
     * Returns the number of columns in the Layout. For a one-dimensional Layout, this is the number of values in the Layout.
     * @return the number of the columns
     */
    public int columnCount() {
        if(this.array.length != 0)
            return this.array[0].length;
        return 0;
    }
    
    /**
     * Returns a new Layout containing values from row firstRow to row lastRow, inclusive, of the recipient Layout.
     * @param firstRow is the start row
     * @param lastRow is the end row
     * @return new layout
     */
    public Layout rows(int firstRow, int lastRow) {
        int[][] rowArray = null;
        if(firstRow >= 0 && firstRow < this.array.length && lastRow >= 0 && lastRow < this.array.length && firstRow <= lastRow) {
            rowArray = new int[lastRow - firstRow + 1][this.array[0].length];
            for(int i = firstRow; i <= lastRow; ++ i)
                for(int j = 0; j < this.array[0].length; ++ j)
                    rowArray[i - firstRow][j] = this.array[i][j];
        }else {
            throw new IllegalArgumentException("Illegal Arguments!");
        }
        return new Layout(rowArray);
    }
    
    /**
     * Returns a new Layout containing values from column firstColumn to column lastColumn, inclusive, of the recipient Layout.
     * @param firstColumn is the start column
     * @param lastColumn is the end comlumn
     * @return new layout
     */
    public Layout columns(int firstColumn, int lastColumn) {
        int[][] colArray = null;
        if(firstColumn >= 0 && firstColumn < this.array.length && lastColumn >= 0 && lastColumn < this.array.length && firstColumn <= lastColumn) {
            colArray = new int[this.array.length][lastColumn - firstColumn + 1];
            for(int i = 0; i < this.array.length; ++ i)
                for(int j = firstColumn; j <= lastColumn; ++ j)
                    colArray[i][j - firstColumn] = this.array[i][j];
        }else {
            throw new IllegalArgumentException("Illegal Arguments!");
        }
        return new Layout(colArray);
    }
    
    /**
     * Returns a new Layout containing values from the given portion of the recipient Layout.
     * @param firstRow is the start row
     * @param lastRow is the end row
     * @param firstColumn is the start column
     * @param lastColumn is the end column
     * @return new layout
     */
    public Layout slice(int firstRow, int lastRow, int firstColumn, int lastColumn) {
        Layout midLayout = this.rows(firstRow, lastRow);
        Layout sliLayout = midLayout.columns(firstColumn, lastColumn);
        return sliLayout;
    }
    
    /**
     * Returns a new Layout in which the parameter layout replaces the values of the recipient Layout, starting at the 
     * given row and column. This method throws an IllegalArgumentException if the parameter Layout would go beyond the 
     * bounds of the recipient layout.
     * @param layout is the layout to be replaced with
     * @param row is the start row
     * @param column is the start column
     * @return new column
     */
    public Layout replace(Layout layout, int row, int column) {
        int[][] otherArray = layout.toArray2D();
        int[][] resultArray = new int[this.array.length][this.array[0].length];
        int r = otherArray.length;
        int c = otherArray[0].length;
        if(row < 0 || column < 0 || row + r - 1 >= this.array.length || column + c - 1 >= this.array[0].length)
            throw new IllegalArgumentException("Illegal Arguments!");
        else {
            for(int i = 0; i < resultArray.length; ++ i)
                for(int j = 0; j < resultArray[0].length; ++ j)
                    resultArray[i][j] = this.array[i][j];
            for(int i = row; i < row + r; ++ i)
                for(int j = column; j < column + c; ++ j)
                    resultArray[i][j] = otherArray[i - row][j - column];
        }
        return new Layout(resultArray);
    }
    
    /**
     * Returns true if and only if this Layout contains an array of the same shape and containing the same values as 
     * that Layout.
     */
    @Override
    public boolean equals(Object that) {
        if (! (that instanceof Layout)) return false;
        Layout l = (Layout)that;
        if (this.rowCount() != l.rowCount()) return false;
        if (this.columnCount() != l.columnCount()) return false;
        return java.util.Arrays.equals(this.toArray1D(), l.toArray1D());
    }
    
    /**
     *Returns a one-dimensional array of the values in the recipient Layout. If the Layout is two-dimensional,
     * it is first unraveled. 
     * @return a one-dimensional array
     */
    public int[] toArray1D() {
        Layout layout1D = this.unravel();
        int[] array = new int[layout1D.columnCount()]; 
        for(int i = 0; i < layout1D.columnCount(); ++ i)
            array[i] = layout1D.at(0, i);
        return array;
    }
    
    /**
     * Returns a two-dimensional array of the values in the recipient Layout. If the Layout is one-dimensional, 
     * the result will be an array containing, as its single element, a one-dimensional array.
     * @return a two-dimensional array
     */
    public int[][] toArray2D(){
        int[][] copyArray;
        if(this.array.length == 0)
            copyArray = new int[0][0];
        else
            copyArray = new int[this.array.length][this.array[0].length];
            for(int i = 0; i < this.array.length; ++ i) {
                for(int j = 0; j < this.array[0].length; ++ j) {
                    copyArray[i][j] = this.array[i][j];
                }
            }
        return copyArray;
    }
    
    /**
     * Returns the integer at the given row and column.
     * @param row is the row of the wanted element
     * @param column is the column of the wanted element
     * @return an element of the layout
     */
    public int at(int row, int column) {
        if(row >= this.array.length || column >= this.array[0].length || row < 0 || column < 0)
            throw new IllegalArgumentException("Index out of bounds.");
        else
            return this.array[row][column];
    }
    
}
    