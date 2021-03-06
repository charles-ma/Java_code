package sequences;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	Queue<Integer> q;

	@Before
	public void setUp() throws Exception {
		q = new Queue<Integer>();
	}

	@Test
	public void testQueue(){
		assertTrue("Queue: Constructor is successful", q instanceof Queue);
	}

	@Test
	public void testIsEmpty() {
		//Empty is really empty
		assertTrue("Queue: Empty is really empty", q.isEmpty());

		//After adding something, empty returns false
		q.add(10);
		assertFalse("Queue: After adding something, empty returns false", q.isEmpty());

		//After removing everything, empty is true again
		q.remove();
		assertTrue("Queue: After removing everything, empty is true again", q.isEmpty());

	}

	@Test
	public void testSize() {
		//Empty queue has size zero
		assertEquals("Queue: Empty queue has size zero",0, q.size());

		//Add an item and size is 1
		q.add(10);
		assertEquals("Queue: Add an item and size is 1", 1, q.size());

		//Add another item, size 2
		q.add(20);
		assertEquals("Queue: Add another item, size 2", 2, q.size());

		//Remove an item and size should be 1
		q.remove();
		assertEquals("Queue: Remove an item and size should be 1", 1, q.size());
	}

	@Test
	public void testClear() {
		//Sanity check
		assertTrue("Queue: Empty initially. Sanity Check", q.isEmpty());

		// Clear should remove all items
		q.add(10);
		q.add(20);
		q.add(30);
		q.clear();

		assertTrue("Queue: Clear should remove all items", q.isEmpty());	
	}


	@Test
	public void testAdd() {
		q.add(5);
		q.add(5);
		q.add(5);
		assertFalse("Queue: Adding things makes it not empty", q.isEmpty());
		assertEquals("Queue: Size is correct after adding items", 3, q.size());
	}

	@Test
	public void testRemove() {
		q.add(10);
		q.add(20);
		q.add(30);
		assertEquals("Queue: Removes correct element", 10, (int)q.remove());
		assertEquals("Queue: Removes correct element", 20, (int)q.remove());
		assertEquals("Queue: Removes correct element", 30, (int)q.remove());
	}

	@Test
	public void testPeek() {
		//Peek checks the item ready to be removed
		q.add(10);
		assertEquals("Queue: Peek checks the item ready to be removed", 10, (int)q.peek());

		// Adding to queue doesn't change item peek returns
		q.add(20);
		assertEquals("Queue: Adding to queue doesn't change item peek returns", 10, (int)q.peek());

		//Removing an item, peek should show next in line
		q.remove();
		assertEquals("Queue: Removing an item, peek should show next in line", 20, (int)q.peek());

		//Peek at empty queue?
		q.clear();
		//
	}

	@Test
	public void testIterator() {
		q.add(10);
		q.add(20);
		q.add(30);
		Iterator<Integer> iter = q.iterator();

		assertTrue("Queue: New iterator has next: true", iter.hasNext());
		assertEquals("Queue: Iterator removes correct element", 10, (int)iter.next());
		assertTrue("Queue: Iterator still has next", iter.hasNext());
		assertEquals("Queue: Iterator removes correct element", 20, (int)iter.next());
		assertTrue("Queue: Iterator still has next", iter.hasNext());
		assertEquals("Queue: Iterator removes correct element", 30, (int)iter.next());
		assertFalse("Queue: Iterator has been expended", iter.hasNext());
	}
}
