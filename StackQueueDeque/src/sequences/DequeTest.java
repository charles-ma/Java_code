/**
 * Test class for Deque
 */
package sequences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {
	private Deque<Integer> d;

	@Before
	public void setUp() throws Exception {
		d = new Deque<Integer>();
	}
	

    /**
     * Test method for {@link sequences.Deque#Deque()}.
     */
    @Test
    public final void testDeque() {
       assertEquals("testing deque constructor", "[]", d.toString());
    }

    /**
     * Test method for {@link sequences.Deque#isEmpty()}.
     */
    @Test
    public final void testIsEmpty() {
    	// Empty is empty
    	assertTrue("testing empty deque  is empty", d.isEmpty());
    	
    	//Adding an item is not empty
    	d.add(5);
    	assertFalse("testing adding an item to deque  is not empty", d.isEmpty());
    	
    	//Removing the last item makes it empty 
    	d.remove();
    	assertTrue("testing removing the last item from deque makes it empty", d.isEmpty());        
    }

    /**
     * Test method for {@link sequences.Deque#clear()}.
     */
    @Test
    public final void testClear() {
    	d.add(5);
    	d.add(5);
    	assertFalse("testing that deque contains items",d.isEmpty());
    	d.clear();
    	assertTrue("testing that deque is cleared", d.isEmpty());   
    }

    /**
     * Test method for {@link sequences.Deque#size()}.
     */
    @Test
    public final void testSize() {
    	assertEquals("testing that deque is empty",0, d.size());
    	d.add(5);
    	assertEquals("testing that deque contains one item after one add", 1, d.size());
    	d.add(5);
    	assertEquals("testing that deque contains two items after two adds", 2, d.size());
    	d.remove();
    	assertEquals("testing that deque contains one items after one remove", 1, d.size());
    }

    /**
     * Test method for {@link sequences.Deque#add(java.lang.Object)}.
     */
    @Test
    public final void testAdd() {
    	// Adding an item makes it not empty
    	d.add(5);
    	assertFalse("Adding an item makes deque is not empty", d.isEmpty());
    	
    	
    	d.add(5);
    	d.add(5);
    	assertFalse("making sure that deque is still not empty after mode adds", d.isEmpty());
    	
    	//After adding n items, size is n
    	assertEquals("After adding n items to deque, size is n", 3, d.size());
    }

    /** 
     * Test method for {@link sequences.Deque#addFront(java.lang.Object)}.
     */
    @Test
    public final void testAddFront(){
    	//addFront works to add an initial item
    	d.addFront(1);
    	assertEquals("addFront works to add an initial item to deque", 1, d.size());
    	
    	
    	d.add(2);
    	d.add(3);
    	
    	// peek checks front most element
    	assertEquals("peek checks front most element in deque", 1, (int)d.peek());

    	// When adding to an already populated deque, addFront, adds to front 
    	d.addFront(0);
    	assertEquals("When adding to an already populated deque, addFront, adds to front ", 0, (int)d.peek());
    }

    /**
     * Test method for {@link sequences.Deque#remove()}.
     */
    @Test
    public final void testRemove() {
    	//Removing and item, removes front most item
    	d.add(10);    	
    	d.add(20);
    	d.add(30);
    	
    	assertEquals("testing deque after front removal of one item", 10, (int)d.remove());
    	assertEquals("testing deque after front removal of two items", 20, (int)d.remove());
    	assertEquals("testing deque after front removal of two items", 30, (int)d.remove());
    }
    
    /**
     * 
     */
    @Test
    public final void testRemoveRear(){
    	//Removes rear most item
    	d.add(10);
    	d.add(20);
    	d.add(30);
    	assertEquals("testing deque after rear removal of one item", 30, (int)d.removeRear());
    	assertEquals("testing deque after rear removal of two items",20, (int)d.removeRear());
    	assertEquals("testing deque after rear removal of three items",10, (int)d.removeRear());
    	
    	assertTrue(d.isEmpty());
    	
    	
    }

    /**
     * Test method for {@link sequences.Deque#peek()}.
     */
    @Test
    public final void testPeek() {
    	d.add(10);
    	d.add(20);
    	d.add(30); 
    	assertEquals("peeking for first item in deque", 10, (int)d.peek());
    	assertEquals("peeking for first item in deque again", 10, (int)d.peek());
    	d.remove();
    	assertEquals("peeking for second item in deque after remove", 20, (int)d.peek());
    }
    
    /**
     * Test method for {@link sequences.Deque#peekRear()}.
     */
    @Test
    public final void testPeekRear() {
    	d.add(10);
    	d.add(20);
    	d.add(30);
    	assertEquals("peeking on rear of deque", 30, (int)d.peekRear());
    	assertEquals("peeking on rear of deque again", 30, (int)d.peekRear());
    	d.removeRear();
    	assertEquals("peeking on rear of deque after remove", 20, (int)d.peekRear());
    }

    /**
     * Test method for {@link sequences.Deque#iterator()}.
     */
    @Test
    public final void testIteratorForward() {
    	d.add(10);
    	d.add(20);
    	d.add(30);
    	Iterator<Integer> iter = d.iterator();
    	assertTrue("making sure that deque has next", iter.hasNext());
    	assertEquals("testing that deque has first item", 10, (int)iter.next());
    	assertTrue("making sure that deque has next after first iteration", iter.hasNext());
    	assertEquals("testing deque has second item", 20, (int)iter.next());
    	assertTrue("making sure that deque has next after second iteration",iter.hasNext());
    	assertEquals("testing deque has third item", 30, (int)iter.next());
    	assertFalse("making sure that deque is empty after iterations", iter.hasNext());
    }

    /**
     * Test method for {@link sequences.Deque#reverseIterator()}
     */
    @Test
    public final void testIteratorBackward(){
    	d.add(10);
    	d.add(20);
    	d.add(30);
    	Iterator<Integer> revIter = d.reverseIterator();

    	assertTrue("making sure that deque has next", revIter.hasNext());
    	assertEquals("testing that deque has third item", 30, (int)revIter.next());
    	assertTrue("making sure that deque has next after first iteration", revIter.hasNext());
    	assertEquals("testing deque has second item", 20, (int)revIter.next());
    	assertTrue("making sure that deque has next after second iteration",revIter.hasNext());
    	assertEquals("testing that deque has first item", 10, (int)revIter.next());
    	assertFalse("making sure that deque is empty after reverse iterations", revIter.hasNext());

    }

}
