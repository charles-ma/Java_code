package tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {

	Tree<String> level2_0;
	Tree<String> level2_1;
	Tree<String> level1_0;
	Tree<String> level1_1;
	Tree<String> level0;
	
	@Before
	public void setUp() {
		this.level2_0 = new Tree<String>("2_0");
		this.level2_1 = new Tree<String>("2_1");
		this.level1_0 = new Tree<String>("1_0", level2_0, level2_1);
		this.level1_1 = new Tree<String>("1_1");
		this.level0 = new Tree<String>("0", level1_0, level1_1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTree() {
		new Tree<String>("circular", level0, level2_0);
	}

	@Test
	public void testSetValue() {
		this.level0.setValue("the root!");
		assertEquals(this.level0.getValue(), "the root!");
		
		assertFalse(this.level1_1.getValue().equals("second level!"));
		this.level1_1.setValue("second level!");
		assertEquals(this.level1_1.getValue(), "second level!");
	}

	@Test
	public void testGetValue() {
		assertEquals(this.level0.getValue(), "0");
		assertEquals(this.level2_0.getValue(), "2_0");
		assertFalse(this.level2_1.getValue().equals("what?"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddChild() {
		Tree<String> addedNode = new Tree<String>("added");
		this.level0.addChild(1, addedNode);
		assertEquals(this.level0.getChild(1), addedNode);
		assertEquals(this.level0.getChild(2), this.level1_1);
		this.level0.addChild(1, this.level2_0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddChildren() {
		Tree<String> added1 = new Tree<String>("");
		Tree<String> added2 = new Tree<String>("");
		Tree<String> added3 = new Tree<String>("");
		this.level0.addChildren(added1, added2, added3);
		assertEquals(this.level0.getChild(2), added1);
		assertEquals(this.level0.getChild(3), added2);
		assertEquals(this.level0.getChild(4), added3);
		this.level0.addChildren(this.level1_0, this.level2_0);
	}

	@Test
	public void testGetNumberOfChildren() {
		assertEquals(this.level0.getNumberOfChildren(), 2);
		assertEquals(this.level1_0.getNumberOfChildren(), 2);
		assertEquals(this.level1_1.getNumberOfChildren(), 0);
		assertEquals(this.level2_1.getNumberOfChildren(), 0);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetChild() {
		assertEquals(this.level0.getChild(0), this.level1_0);
		assertEquals(this.level0.getChild(1), this.level1_1);
		assertEquals(this.level1_0.getChild(0), this.level2_0);
		assertEquals(this.level1_0.getChild(1), this.level2_1);
		this.level0.getChild(2);
	}

	@Test
	public void testIterator() {
		Iterator<Tree<String>> ite = this.level0.iterator();
		assertTrue(ite.hasNext());
		assertEquals(ite.next(), this.level1_0);
		assertTrue(ite.hasNext());
		assertEquals(ite.next(), this.level1_1);
		assertFalse(ite.hasNext());
	}

	@Test
	public void testContains() {
		assertTrue(this.level0.contains(this.level0));
		assertTrue(this.level0.contains(this.level1_0));
		assertTrue(this.level0.contains(this.level1_1));
		assertTrue(this.level0.contains(this.level2_0));
		assertTrue(this.level0.contains(this.level2_1));
		assertTrue(this.level1_0.contains(this.level2_0));
		assertFalse(this.level1_1.contains(this.level2_0));
		assertFalse(this.level1_1.contains(this.level0));
	}

	@Test
	public void testParse() {
		String input1 = "one( two  three( four five (six  seven eight))  )";
		Tree<String> tree = Tree.parse(input1);
		tree.print();
		assertEquals(tree.getValue(), "one");
		assertEquals(tree.getChild(0).getValue(), "two");
		assertEquals(tree.getChild(1).getChild(1).getValue(), "five");
		//assertEquals(tree.getChild(1).getChild(1).getChild(2).getValue(), "eight");
	}

	@Test
	public void testPrint() {
		this.level0.print();
		System.out.println("------------------");
		this.level1_0.print();
		System.out.println("------------------");
		this.level1_1.print();
		System.out.println("------------------");
	}

	@Test
	public void testToString() {
		assertEquals(this.level0.toString(), "0(1_0(2_0 2_1) 1_1)");
	}

	@Test
	public void testEqualsObject() {
		assertTrue(this.level0.equals(this.level0));
		assertFalse(this.level0.equals(this.level1_0));
		assertFalse(this.level0.equals(this.level2_1));
		assertFalse(this.level0.equals(1));
		assertFalse(this.level0.equals(null));
	}

	@Test
	public void testShareNodes() {
		assertTrue(this.level0.shareNodes(this.level0));
		assertTrue(this.level0.shareNodes(this.level2_0));
		assertFalse(this.level1_1.shareNodes(this.level1_0));
		assertFalse(this.level0.shareNodes(null));
	}
}
