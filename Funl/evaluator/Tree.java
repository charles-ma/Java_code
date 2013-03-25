/**
 * 
 */
package evaluator;

import java.util.ArrayList;

/**
 * Tree used to parse funl in Parser
 * @author machao
 * @version 1.0
 */
public class Tree<T> {
	private T value = null;
	private ArrayList<Tree<T>> children = new ArrayList<Tree<T>>();
	
	/**
	 * Constructor of the inner tree
	 * @param value the value at the root of the tree
	 */
	public Tree(T value) {
		this.value = value;
	}
		
	/**
	 * Make a deep copy of a tree
	 * @param tree the tree to be copied
	 */
	public Tree(Tree<T> tree) {
		value = tree.value;
		for(int i = 0; i < tree.children.size(); i++) {
			children.add(new Tree<T>(tree.children.get(i)));
		}
	}
	
	/**
	 * Adds a child node to the tree
	 * @param child the subtree to be added to the tree
	 */
	public void addChild(Tree<T> child) {
		children.add(child);
	}
	
	/**
	 * Gets the value of the root
	 * @return the value of the root
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Prints out the tree
	 */
	public void print() {
		System.out.println(value.toString());
		for(Tree<T> child : children) {
			child.print();
		}
	}
	
	/**
	 * Gets all the children of a tree node
	 * @return an ArrayList containing all the children
	 */
	public ArrayList<Tree<T>> getChildren() {
		return children;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Tree)) return false;
		Tree<T> other = (Tree<T>) o;
		if(!value.equals(other.value)) return false;
		for(int i = 0; i < children.size(); i++) {
			if(!children.get(i).equals(other.children.get(i))) return false;
		}
		return true;
	}
} 