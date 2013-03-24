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
	
} 