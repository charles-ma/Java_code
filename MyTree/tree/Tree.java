/**
 * tree package contains a tree data structure and a class to evaluate expressions
 * @author machao
 */
package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author machao
 * An implementation of the tree data structure
 */
public class Tree<V> {
	
	private ArrayList<Tree<V>> children = new ArrayList<Tree<V>>();
	private V value = null;
	
	/**
	 * Constructs a Tree with the given value in the root node, having the given children
	 * @param value the value of the root node
	 * @param children the children of the root node
	 * @exception IllegalArgumentException if adding children to the new node will result in a circular tree
	 */
	public Tree(V value, Tree<V>... children) throws IllegalArgumentException {
		this.value = value;
		for(Tree<V> child : children) {
			if(shareNodes(child)) throw new IllegalArgumentException("Circular Tree!");
			this.children.add(child);
		}
	}
	
	/**
	 * Sets the value of the current node to the given value
	 * @param value the value to be set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * Gets the value of the current node
	 * @return the value of the node
	 */
	public V getValue() {
		return this.value;
	}
	
	/**
	 * Adds a tree as the index's child
	 * @param index the index of the node you add the child
	 * @param child the subtree you want to add
	 */
	public void addChild(int index, Tree<V> child) {
		if(shareNodes(child)) throw new IllegalArgumentException("Circular tree!");
		this.children.add(index, child);
		//this.print();
	}
	
	/**
	 * Adds children to the current node after any existing children
	 * @param children the children you want to add to the node
	 */
	public void addChildren(Tree<V>... children){
		for(Tree<V> child : children) addChild(this.children.size(),child);
	} 
	
	/**
	 * Gets the number of the children of the current node
	 * @return the number of children
	 */
	public int getNumberOfChildren() {
		return this.children.size();		
	}
	
	/**
	 * Gets the index's child of the current node
	 * @param index the index of the child
	 * @return the child at a certain index
	 */
	public Tree<V> getChild(int index) {
		return this.children.get(index);
	}
	
	/**
	 * Gets the iterator of the children of the current node
	 * @return the iterator
	 */
	public Iterator<Tree<V>> iterator() {
		return children.iterator();
	}
	
	/**
	 * Tests whether a tree contains a certain node(subtree)
	 * @param node the node(subtree)
	 * @return true of false
	 */
	boolean contains(Tree<V> node) {
		if(node == this) return true;
		boolean result = false;
		for(Tree<V> child : this.children) {
			result = result || child.contains(node);
		}
		return result;
	}
	
	/**
	 * Translates the string description of a tree into a Tree<String>
	 * @param treeDescription the string description
	 * @return a new Tree<String>
	 */
	public static Tree<String> parse(String treeDescription) {
		Tree<String> tree = null;
		if(treeDescription == null) return null;
		treeDescription = treeDescription.trim();
		int parePos = -1;
		for(int i = 0; i < treeDescription.length(); i++) {
			if(treeDescription.charAt(i) == '(') {
				parePos = i;
				break;
			}
		}
		if(parePos == -1) { 
			tree = new Tree<String>(treeDescription.replace(" ", ""));
		} else {
			tree = new Tree<String>(treeDescription.substring(0, parePos).replace(" ", ""));
			String inner = treeDescription.substring(parePos + 1);
			int end = inner.length() - 1;
			for(int i = inner.length() - 1; i >= 0; i--) {
				if(inner.charAt(i) == ')') {
					end = i;
					break;
				} 
			}
			inner = inner.substring(0, end);
			String clean = new String();
			for(int i = 0; i < inner.length(); i++) {
				if(inner.charAt(i) != '(') clean = clean + inner.substring(i, i + 1);
				else {
					while(clean.charAt(clean.length() - 1) == ' ') {
						clean = clean.substring(0, clean.length() - 1);
					}
					clean = clean + inner.substring(i, i + 1);
				}
			}
			clean = clean.trim();
			String[] subDes = clean.split(" ");
			String subDescription = new String("");
			int pCount = 0;
			Tree<String> sub = null;
			for(int i = 0; i < subDes.length; i++) {
				subDes[i] = subDes[i].replace(" ", "");
				if(subDes[i].equals("")) continue;
				if(subDes[i].contains("(")) {
					if(pCount == 0) {
						subDescription = "";	
					}
					subDescription += (subDes[i] + " ");
					for(int k = 0; k < subDes[i].length(); k++) {
						if(subDes[i].charAt(k) == '(') pCount++;
						if(subDes[i].charAt(k) == ')') pCount--;
					}
				} else if(subDes[i].contains(")")) {
					for(int k = 0; k < subDes[i].length(); k++) {
						if(subDes[i].charAt(k) == '(') pCount++;
						if(subDes[i].charAt(k) == ')') pCount--;
					}
					subDescription += (subDes[i] + " ");
					if(pCount == 0) {
						sub = Tree.parse(subDescription);
						tree.addChild(tree.getNumberOfChildren(), sub);
					}
					//sub = new Tree<String>(subDescription);
				} else if(pCount > 0) {
					subDescription += (subDes[i] + " "); 
				} else {
					//sub = new Tree<String>(subDescription);
					sub = Tree.parse(subDes[i]);
					tree.addChild(tree.getNumberOfChildren(), sub);
				}
			}
		}
		return tree;
		
	}
	
	/**
	 * Prints multiline version of the tree
	 */
	public void print() {
		print(0);
	}

	/**
	 * Prints multiline version of the tree with the depth as a parameter
	 * @param depth the depth of the starting node
	 */
	private void print(int depth) {
		for(int i = 0; i < depth; i++) {
			System.out.print("|   ");
		}
		System.out.println(this.value.toString());
		depth++;
		for(Tree<V> child : this.children) child.print(depth);
	}
	
	@Override
	public String toString() {
		String tmp = new String();
		for(int i = 0; i < this.children.size(); i++) {
			tmp = tmp + this.children.get(i).toString();
			if(i != this.children.size() - 1) tmp = tmp + " ";
		} 
		if(getNumberOfChildren() != 0) tmp = this.value.toString() + "(" + tmp + ")";
		else tmp = this.value.toString();
		return tmp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof Tree<?>)) return false;
		Tree<V> other = (Tree<V>) obj;
		if(this.value != other.value) return false;
		if(this.children.size() != other.children.size()) return false;
		for(int i = 0; i < this.children.size(); i++) {
			if(this.children.get(i).value != other.children.get(i).value) return false;
		}
		return true;
	}
	
	/**
	 * Tests whether a tree shares some common nodes with the current tree
	 * @param other the tree to be compared with the current tree
	 * @return a boolean value
	 */
	boolean shareNodes(Tree<V> other) {
		if(other == null) return false;
		if(this.contains(other)) return true;
		else if(other.getNumberOfChildren() == 0) return false;
		else {
			boolean result = false;
			for(Tree<V> sub : other.children) {
				result = result || this.contains(sub);
			}
			return result;
		}
	} 
}
