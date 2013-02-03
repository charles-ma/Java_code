/**
 * 
 */
package tree;

/**
 * @author machao
 * Using tree structure to evaluate some expressions
 */
public class Expression {
	
	Tree<String> tree = null;
	
	/**
	 * Constructor of the class
	 * @param expression the expression to be evaluated
	 */
	public Expression(String expression) throws IllegalArgumentException {
		this.tree = Tree.parse(expression);
		//if(!isValidate(this.tree)) throw new IllegalArgumentException("Invalidate tree!");
	}
	
	/**
	 * Test whether a tree is validate
	 * @param tree the tree to be tested
	 * @return boolean value
	 */
	private boolean isValidate(Tree<String> tree) {
		if(tree.getValue().equals("+") || tree.getValue().equals("*")) {
			if(tree.getNumberOfChildren() >= 2) {
				boolean result = true;
				for(int i = 0; i < tree.getNumberOfChildren(); i++) {
					result = result && isValidate(tree.getChild(i));
				}
				return result;	
			}
			return false;
		} else if(tree.getValue().equals("-") || tree.getValue().equals("/")) {
			if(tree.getNumberOfChildren() == 2) {
				boolean result = true;
				for(int i = 0; i < tree.getNumberOfChildren(); i++) {
					result = result && isValidate(tree.getChild(i));
				}
				return result;
			}
			return false;
		} else if(Integer.parseInt(tree.getValue()) >= 0) {
			if(tree.getNumberOfChildren() == 0) return true;
			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * Evaluate the value of the expression
	 * @return the result
	 */
	public int evaluate() {
		return evaluate(this.tree);
	}
	
	/**
	 * Evaluate the value of a tree
	 * @param tree the tree to be evaluated
	 * @return the result
	 */
	private int evaluate(Tree<String> tree) {
		if(!isValidate(tree)) return 0;
		if(tree.getNumberOfChildren() == 0) return Integer.parseInt(tree.getValue());
		if(tree.getValue().equals("+")) {
			int sum = 0;
			for(int i = 0; i < tree.getNumberOfChildren(); i++) {
				sum = sum + evaluate(tree.getChild(i));
			}
			return sum;
		} else if(tree.getValue().equals("*")) {
			int mul = 1;
			for(int i = 0; i < tree.getNumberOfChildren(); i++) {
				mul = mul * evaluate(tree.getChild(i));
			}
			return mul;
		} else if(tree.getValue().equals("-")) {
			return (evaluate(tree.getChild(0)) - evaluate(tree.getChild(1)));
		} else if(tree.getValue().equals("/")) {
			return (evaluate(tree.getChild(0)) / evaluate(tree.getChild(1)));
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return this.tree.toString();
	}
	
	public void print() {
		this.tree.print();
	}
}
