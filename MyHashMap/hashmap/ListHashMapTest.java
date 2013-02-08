package hashmap;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListHashMapTest {

	private ListHashMap <String, Integer> listHash = new ListHashMap<String, Integer>(10);
	
	@Test
	public void testSize() {
		assertEquals(0, this.listHash.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.listHash.isEmpty());
	}

	@Test
	public void testContainsKey() {
		assertFalse(this.listHash.containsKey("abc"));
	}

	@Test
	public void testContainsValue() {
		assertFalse(this.listHash.containsValue(3));
		assertFalse(this.listHash.containsKey(7));
	}

	@Test
	public void testGet() {
		assertEquals(null, this.listHash.get("abc"));
	}

	@Test
	public void testPut() {
		
	}

	@Test
	public void testRemove() {
		
	}

	@Test
	public void testPutAll() {
		
	}

	@Test
	public void testClear() {
		
	}

	@Test
	public void testKeySet() {
		
	}

	@Test
	public void testValues() {
		
	}

	@Test
	public void testEntrySet() {
		
	}

}
