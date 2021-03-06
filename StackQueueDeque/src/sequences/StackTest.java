/**
 * Test class for Stack.
 */
package sequences;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class StackTest {
    private Stack<Integer> s;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        s = new Stack<Integer>();
    }

    /**
     * Test method for {@link sequences.Stack#Stack()}.
     */
    @Test
    public final void testStack() {
	assertTrue("Constructor doesn't work", s instanceof Stack);
    }

    /**
     * Test method for {@link sequences.Stack#isEmpty()}.
     */
    @Test
    public final void testIsEmpty() {
        assertTrue("Initial stack isn't empty", s.isEmpty());
        s.add(5);
        assertFalse("isEmpty() returned true for nonempty stack", s.isEmpty());
        s.remove();
        assertTrue("isEmpty() returned false after all elements were removed", s.isEmpty());        
    }

    /**
     * Test method for {@link sequences.Stack#clear()}.
     */
    @Test
    public final void testClear() {
        s.add(5);
        s.add(5);
        assertFalse(s.isEmpty());
        s.clear();
        assertTrue("clear() doesn't empty stack", s.isEmpty());   
    }

    /**
     * Test method for {@link sequences.Stack#size()}.
     */
    @Test
    public final void testSize() {
        assertEquals("size() gives incorrect size", 0, s.size());
        s.add(5);
        assertEquals("size() gives incorrect size", 1, s.size());
        s.add(5);
        assertEquals("size() gives incorrect size", 2, s.size());
        s.remove();
        assertEquals("size() gives incorrect size", 1, s.size());
    }

    /**
     * Test method for {@link sequences.Stack#add(java.lang.Object)}.
     */
    @Test
    public final void testAdd() {
        s.add(5);
        s.add(5);
        s.add(5);
        assertFalse("Adding values to stack doesn't work", s.isEmpty());
        assertEquals("size() gives incorrect size", 3, s.size());
    }

    /**
     * Test method for {@link sequences.Stack#remove()}.
     */
    @Test
    public final void testRemove() {
        s.add(10);
        s.add(20);
        s.add(30);
        assertEquals("Stack remove() gives incorrect result", 30, (int)s.remove());
        assertEquals("Stack remove() gives incorrect result", 20, (int)s.remove());
        assertEquals("Stack remove() gives incorrect result", 10, (int)s.remove());
    }

    /**
     * Test method for {@link sequences.Stack#peek()}.
     */
    @Test
    public final void testPeek() {
        s.add(10);
        s.add(20);
        s.add(30);
        assertEquals("peek() of stack gives incorrect result", 30, (int)s.peek());
        assertEquals("peek() of stack modifies the stack", 30, (int)s.peek());
        s.remove();
        assertEquals("peek() of stack gives incorrect result", 20, (int)s.peek());
    }

    /**
     * Test method for {@link sequences.Stack#iterator()}.
     */
    @Test
    public final void testIterator() {
    	s.add(10);
        s.add(20);
        s.add(30);
        Iterator<Integer> iter = s.iterator();
        iter.hasNext();
        assertTrue("Stack hasNext() not working", iter.hasNext());
        assertEquals("Stack next() not working", 30, (int)iter.next());
        assertTrue("Stack hasNext() not working", iter.hasNext());
        assertEquals("Stack next() not working", 20, (int)iter.next());
        assertTrue("Stack hasNext() not working", iter.hasNext());
        assertEquals("Stack next() not working", 10, (int)iter.next());
        assertFalse("hasNext() gives true on empty stack", iter.hasNext());
    }
}
