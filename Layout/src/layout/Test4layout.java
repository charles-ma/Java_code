package layout;

//Author Chao Ma
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test4layout {
    Layout example1;
    Layout example2;
    Layout example3;
    Layout example4;
    
    @Before
    public void setUp() throws Exception {
        example1 = new Layout(new int[] {1,2,3,4,5});
        example2 = new Layout(new int[][] {{1,2,3}, {4,5,6}, {7,8,9}});
        example3 = new Layout(3);
        example4 = new Layout(new int[][] {{1, 2}, {3, 4}, {5, 6}});
    }
    
    @Test
    public final void testLayoutIntArray() {
        assertEquals(example1.rowCount(), 1);
        assertEquals(example1.columnCount(), 5);
        assertArrayEquals(example1.toArray2D(), new int[][] {{1,2,3,4,5}});
    }

    @Test
    public final void testLayoutIntArrayArray() {
        assertEquals(example2.rowCount(), 3);
        assertEquals(example2.columnCount(), 3);
        assertArrayEquals(example2.toArray2D(), new int[][] {{1,2,3}, {4,5,6}, {7,8,9}});
    }

    @Test
    public final void testLayoutInt() {
        assertEquals(example3.rowCount(), 1);
        assertEquals(example3.columnCount(), 3);
        assertArrayEquals(example3.toArray2D(), new int[][] {{1,2,3}});
    }

    @Test
    public final void testReverse() {
        assertEquals(example1.reverse(), new Layout(new int[] {5,4,3,2,1}));
        assertEquals(example2.reverse(), new Layout(new int[][] {{3,2,1}, {6,5,4}, {9,8,7}}));
        assertEquals(example3.reverse(), new Layout(new int[] {3,2,1}));
        assertFalse(example1.reverse().equals(example2.reverse()));
    }

    @Test
    public final void testRotateRight() {
        assertEquals(example1.rotateRight(), new Layout(new int[][] {{1}, {2}, {3}, {4}, {5}}));
        assertEquals(example2.rotateRight(), new Layout(new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}));
        assertEquals(example3.rotateRight(), new Layout(new int[][] {{1}, {2}, {3}}));
        assertEquals(example4.rotateRight(), new Layout(new int[][] {{5, 3, 1}, {6, 4, 2}}));
        assertFalse(example1.rotateRight().equals(example2.rotateRight()));
    }

    @Test
    public final void testRotateLeft() {
        assertEquals(example1.rotateLeft(), new Layout(new int[][] {{5}, {4}, {3}, {2}, {1}}));
        assertEquals(example2.rotateLeft(), new Layout(new int[][] {{3, 6, 9}, {2, 5, 8}, {1, 4, 7}}));
        assertEquals(example3.rotateLeft(), new Layout(new int[][] {{3}, {2}, {1}}));
        assertEquals(example4.rotateLeft(), new Layout(new int[][] {{2, 4, 6}, {1, 3, 5}}));
        assertFalse(example1.rotateLeft().equals(example2.rotateLeft()));
    }
    

    @Test
    public final void testTranspose() {
        assertEquals(example1.transpose(), new Layout(new int[][] {{1}, {2}, {3}, {4}, {5}}));
        assertEquals(example2.transpose(), new Layout(new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}));
        assertEquals(example3.transpose(), new Layout(new int[][] {{1}, {2}, {3}}));
        assertEquals(example4.transpose(), new Layout(new int[][] {{1, 3, 5}, {2, 4, 6}}));
        assertFalse(example1.transpose().equals(example2.transpose()));
    }

    @Test
    public final void testRavel() {
        assertEquals(example1.ravel(1), new Layout(new int[][] {{1}, {2}, {3}, {4}, {5}}));
        Throwable e = null;
        try {
            example1.ravel(2);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);
        try {
            example2.ravel(2);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof UnsupportedOperationException);
    }

    @Test
    public final void testUnravel() {
        assertEquals(example1.unravel(), new Layout(new int[] {1, 2, 3, 4, 5}));
        assertEquals(example2.unravel(), new Layout(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}));
        assertEquals(example3.unravel(), new Layout(new int[] {1, 2, 3}));
        assertEquals(example4.unravel(), new Layout(new int[] {1, 2, 3, 4, 5, 6}));
        assertFalse(example1.unravel().equals(example2.unravel()));
    }

    @Test
    public final void testReshape() {
        assertEquals(example1.reshape(1), new Layout(new int[][] {{1}, {2}, {3}, {4}, {5}}));
        assertEquals(example4.reshape(3), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        Throwable e = null;
        try {
            example1.reshape(2);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);
        assertEquals(example2.reshape(3), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public final void testJoin() {
        assertEquals(example1.join(new Layout(new int[] {6})), new Layout(new int[] {1, 2, 3, 4, 5, 6}));
        assertEquals(example2.join(new Layout(new int[][] {{10}, {11}, {12}})), new Layout(new int[][] {{1 ,2 ,3, 10}, {4, 5, 6, 11}, {7, 8, 9, 12}}));
        Throwable e = null;
        try {
            example2.join(new Layout(new int[] {1 ,2, 3}));
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    @Test
    public final void testStack() {
        assertEquals(example1.stack(new Layout(new int[] {6, 7, 8, 9, 10})), new Layout(new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}}));
        assertEquals(example2.stack(new Layout(new int[] {10, 11, 12})), new Layout(new int[][] {{1 ,2 ,3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}));
        Throwable e = null;
        try {
            example2.stack(new Layout(new int[] {1 ,2}));
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    @Test
    public final void testRowCount() {
        assertEquals(example1.rowCount(), 1);
        assertEquals(example2.rowCount(), 3);
        assertEquals(example3.rowCount(), 1);
        assertEquals(example4.rowCount(), 3);
        //fail("Not yet implemented");
    }

    @Test
    public final void testColumnCount() {
        assertEquals(example1.columnCount(), 5);
        assertEquals(example2.columnCount(), 3);
        assertEquals(example3.columnCount(), 3);
        assertEquals(example4.columnCount(), 2);
        //fail("Not yet implemented");
    }

    @Test
    public final void testRows() {
        assertEquals(example2.rows(0, 1), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        assertEquals(example2.rows(0, 0), new Layout(new int[] {1, 2, 3}));
        assertEquals(example2.rows(0, 2), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        /*Throwable e = null;
        try {
            example2.rows(1, 3);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);*/
    }

    @Test
    public final void testColumns() {
        assertEquals(example2.columns(0, 1), new Layout(new int[][] {{1, 2}, {4, 5}, {7, 8}}));
        assertEquals(example2.columns(0, 0), new Layout(new int[][] {{1}, {4}, {7}}));
        assertEquals(example2.columns(0, 2), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        /*Throwable e = null;
        try {
            example2.columns(1, 3);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);*/
    }

    @Test
    public final void testSlice() {
        assertEquals(example2.slice(0, 1, 0, 1), new Layout(new int[][] {{1, 2}, {4, 5}}));
        assertEquals(example2.slice(0, 0, 0, 0), new Layout(new int[] {1}));
        assertEquals(example2.slice(0, 2, 0, 2), new Layout(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        /*Throwable e = null;
        try {
            example2.slice(1, 3, 0, 1);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);*/
    }

    @Test
    public final void testReplace() {
        Layout l = new Layout(new int[][] {{15, 15}, {15, 15}});
        assertEquals(example2.replace(l, 0, 0), new Layout(new int[][] {{15, 15, 3}, {15, 15, 6}, {7, 8, 9}}));
        assertEquals(example2.replace(l, 0, 1), new Layout(new int[][] {{1, 15, 15}, {4, 15, 15}, {7, 8, 9}}));
        assertEquals(example2.replace(l, 1, 1), new Layout(new int[][] {{1, 2, 3}, {4, 15, 15}, {7, 15, 15}}));
        Throwable e = null;
        try {
            example2.replace(l, 0, 2);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    @Test
    public final void testEqualsObject() {
        Layout l0 = new Layout(0);
        Layout l1 = new Layout(5);
        Layout l2 = new Layout(new int[] { 1, 2, 3 });
        Layout l3 = new Layout(new int[][] { {2, 3}, {4, 5}, {6, 7}, {8, 1} });
        Layout m0 = new Layout(0);
        Layout m1 = new Layout(5);
        Layout m2 = new Layout(new int[] { 1, 2, 3 });
        Layout m3 = new Layout(new int[][] { {2, 3}, {4, 5}, {6, 7}, {8, 1} });
        assertEquals(l0, m0);
        assertEquals(l1, m1);
        assertEquals(l2, m2);
        assertEquals(l3, m3);
        assertFalse(l0.equals(l1));
        assertFalse(l1.equals(l0));
        assertFalse(l1.equals(l2));
        assertFalse(l2.equals(l3));
        assertFalse(l3.equals(l1));
        assertFalse(l2.equals(new int[] {1, 3, 2}));
        assertFalse(l3.equals(new int[] { 2, 3, 4, 5, 6, 7, 8, 1 }));
        assertFalse(l0.equals(null));
    }

    @Test
    public final void testToArray1D() {
        assertArrayEquals(example1.toArray1D(), new int[] {1, 2, 3, 4, 5});
        assertArrayEquals(example2.toArray1D(), new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertArrayEquals(example4.toArray1D(), new int[] {1, 2, 3, 4, 5, 6});
    }

    @Test
    public final void testToArray2D() {
        assertArrayEquals(example1.toArray2D(), new int[][] {{1, 2, 3, 4, 5}});
        assertArrayEquals(example2.toArray2D(), new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertArrayEquals(example3.toArray2D(), new int[][] {{1, 2, 3}});
        assertArrayEquals(example4.toArray2D(), new int[][] {{1, 2}, {3, 4}, {5, 6}});
        //fail("Not yet implemented");
    }

    @Test
    public final void testAt() {
        assertEquals(example1.at(0, 0), 1);
        assertEquals(example1.at(0, 3), 4);
        assertEquals(example2.at(0, 2), 3);
        assertEquals(example2.at(2, 1), 8);
        /*Throwable e = null;
        try {
            example2.at(2, 3);
        }
        catch(Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof IllegalArgumentException);*/
    }

}
