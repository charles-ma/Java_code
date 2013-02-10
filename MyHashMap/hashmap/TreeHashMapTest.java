package hashmap;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TreeHashMapTest {

	private TreeHashMap <String, Integer> treeHash = new TreeHashMap<String, Integer>(10);
	
	@Test
	public void testSize() {
		assertEquals(0, this.treeHash.size());
		this.treeHash.put("first", 1);
		this.treeHash.put("first", 1);
		this.treeHash.put("second", 2);
		assertEquals(2, this.treeHash.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.treeHash.isEmpty());
		this.treeHash.put("first", 1);
		assertFalse(this.treeHash.isEmpty());
	}

	@Test
	public void testContainsKey() {
		assertFalse(this.treeHash.containsKey("abc"));
		this.treeHash.put("first", 1);
		assertTrue(this.treeHash.containsKey("first"));
	}

	@Test
	public void testContainsValue() {
		assertFalse(this.treeHash.containsValue(3));
		assertFalse(this.treeHash.containsKey(7));
		this.treeHash.put("first", 1);
		this.treeHash.put("first", 2);
		assertFalse(this.treeHash.containsValue(1));
		assertTrue(this.treeHash.containsValue(2));
	}

	@Test
	public void testGet() {
		assertEquals(null, this.treeHash.get("abc"));
		this.treeHash.put("first", 1);
		this.treeHash.put("first", 2);
		assertEquals(new Integer(2), this.treeHash.get("first"));
		assertFalse(new Integer(1) == this.treeHash.get("first"));
	}

	@Test
	public void testPut() {
		assertEquals(new Integer(2), this.treeHash.put("first", 2));
		assertTrue(this.treeHash.containsKey("first"));
		assertEquals(new Integer(2), this.treeHash.get("first"));
		assertEquals(new Integer(3), this.treeHash.put("second", 3));
		assertFalse(this.treeHash.containsKey("third"));
		assertFalse(new Integer(2) == this.treeHash.get("second"));
		assertEquals(null, this.treeHash.put(null, 6));
		assertEquals(new Integer(4), this.treeHash.put("first", 4));
		assertEquals(new Integer(4), this.treeHash.get("first"));
		assertFalse(new Integer(4) == this.treeHash.get("first"));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemove() {
		this.treeHash.remove("");
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testPutAll() {
		this.treeHash.putAll(new ListHashMap<String, Integer> (10));
	}

	@Test
	public void testClear() {
		this.treeHash.put("first", 1);
		this.treeHash.put("second", 2);
		this.treeHash.put("third", 3);
		this.treeHash.clear();
		assertTrue(this.treeHash.isEmpty());
	}

	@Test
	public void testKeySet() {
		this.treeHash.put("first", 1);
		this.treeHash.put("second", 2);
		this.treeHash.put("third", 3);
		Set<String> keySet = this.treeHash.keySet();
		assertEquals(3, keySet.size());
		assertTrue(keySet.contains("first"));
		assertTrue(keySet.contains("second"));
		assertTrue(keySet.contains("third"));
		assertFalse(keySet.contains("fourth"));
		this.treeHash.clear();
		keySet = this.treeHash.keySet();
		assertEquals(0, keySet.size());
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testValues() {
		this.treeHash.values();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testEntrySet() {
		this.treeHash.entrySet();
	}

	@Test
	public void testEquals() {
		ListHashMap<String, Integer> other = null;
		assertFalse(this.treeHash.equals(other));
		other = new ListHashMap<String, Integer>(10);
		this.treeHash.put("first", 1);
		this.treeHash.put("second", 2);
		this.treeHash.put("third", 3);
		other.put("first", 1);
		other.put("second", 2);
		other.put("third", 3);
		assertEquals(this.treeHash, other);
		other.put("fourth", 4);
		assertFalse(this.treeHash.equals(other));
	}
}

