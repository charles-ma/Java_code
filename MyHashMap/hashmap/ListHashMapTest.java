package hashmap;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class ListHashMapTest {

	private ListHashMap <String, Integer> listHash = new ListHashMap<String, Integer>(10);
	
	@Test
	public void testSize() {
		assertEquals(0, this.listHash.size());
		this.listHash.put("first", 1);
		this.listHash.put("first", 1);
		this.listHash.put("second", 2);
		assertEquals(2, this.listHash.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.listHash.isEmpty());
		this.listHash.put("first", 1);
		assertFalse(this.listHash.isEmpty());
	}

	@Test
	public void testContainsKey() {
		assertFalse(this.listHash.containsKey("abc"));
		this.listHash.put("first", 1);
		assertTrue(this.listHash.containsKey("first"));
	}

	@Test
	public void testContainsValue() {
		assertFalse(this.listHash.containsValue(3));
		assertFalse(this.listHash.containsKey(7));
		this.listHash.put("first", 1);
		this.listHash.put("first", 2);
		assertFalse(this.listHash.containsValue(1));
		assertTrue(this.listHash.containsValue(2));
	}

	@Test
	public void testGet() {
		assertEquals(null, this.listHash.get("abc"));
		this.listHash.put("first", 1);
		this.listHash.put("first", 2);
		assertEquals(new Integer(2), this.listHash.get("first"));
		assertFalse(new Integer(1) == this.listHash.get("first"));
	}

	@Test
	public void testPut() {
		assertEquals(new Integer(2), this.listHash.put("first", 2));
		assertTrue(this.listHash.containsKey("first"));
		assertEquals(new Integer(2), this.listHash.get("first"));
		assertEquals(new Integer(3), this.listHash.put("second", 3));
		assertFalse(this.listHash.containsKey("third"));
		assertFalse(new Integer(2) == this.listHash.get("second"));
		assertEquals(null, this.listHash.put(null, 6));
		assertEquals(new Integer(4), this.listHash.put("first", 4));
		assertEquals(new Integer(4), this.listHash.get("first"));
		assertFalse(new Integer(4) == this.listHash.get("first"));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemove() {
		this.listHash.remove("");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testPutAll() {
		this.listHash.putAll(new ListHashMap<String, Integer> (10));
	}

	@Test
	public void testClear() {
		this.listHash.put("first", 1);
		this.listHash.put("second", 2);
		this.listHash.put("third", 3);
		this.listHash.clear();
		assertTrue(this.listHash.isEmpty());
	}

	@Test
	public void testKeySet() {
		this.listHash.put("first", 1);
		this.listHash.put("second", 2);
		this.listHash.put("third", 3);
		Set<String> keySet = this.listHash.keySet();
		assertEquals(3, keySet.size());
		assertTrue(keySet.contains("first"));
		assertTrue(keySet.contains("second"));
		assertTrue(keySet.contains("third"));
		assertFalse(keySet.contains("fourth"));
		this.listHash.clear();
		keySet = this.listHash.keySet();
		assertEquals(0, keySet.size());
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testValues() {
		this.listHash.values();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testEntrySet() {
		this.listHash.entrySet();
	}

	@Test
	public void testEquals() {
		ListHashMap<String, Integer> other = null;
		assertFalse(this.listHash.equals(other));
		other = new ListHashMap<String, Integer>(10);
		this.listHash.put("first", 1);
		this.listHash.put("second", 2);
		this.listHash.put("third", 3);
		other.put("first", 1);
		other.put("second", 2);
		other.put("third", 3);
		assertEquals(this.listHash, other);
		other.put("fourth", 4);
		assertFalse(this.listHash.equals(other));
	}
}
