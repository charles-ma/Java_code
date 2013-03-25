/**
 * 
 */
package evaluator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.JFileChooser;

/**
 * This class contains the main function to run the REPL
 * @author machao
 * @version 1.0
 */
public class Funl {

	private Scanner scanner = new Scanner(System.in);
	private boolean isStop = false;
	private Parser parser = null;
	private HashMap<String, Tree<Token>> functionTrees = new HashMap<String, Tree<Token>>();
	private Stack<HashMap<String, Tree<Token>>> funcScope = new Stack<HashMap<String, Tree<Token>>>();
	private HashMap<String, Tree<Token>> REPLScope = new HashMap<String, Tree<Token>>();
	
	
	/**
	 * The starting point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		new Funl().runREPL();
	}
	

	/**
	 * Function to run the REPL
	 */
	public void runREPL() {
		while(!isStop) {
			System.out.print("Funl>> ");
			String input = scanner.nextLine();
			if(input.equals("quit")) {
				isStop = true;
				System.out.println("Funl>> Good bye!");
			} else if(input.equals("load")) {
				String output = chooseFile();
				if(!output.equals("")) System.out.println("Function Loaded: " + output);
			} else {
				System.out.println(evaluateInput(input));
				//System.out.println("size is " + REPLScope.size());
				//System.out.println("fun size is " + funcScope.size());
			}
		}
	}
	
	
	/**
	 * Generate file chooser
	 */
	public String chooseFile() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);
		String functions = "";
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				try {
					String line = reader.readLine();
					while(line != null) {
						functions += line + " ";
						line = reader.readLine();
					}
				} catch (IOException e) {
					System.out.println("Read file error!");
				}
				if(!functions.equals("")) {
					define(functions);
					return functionTrees.keySet().toString();
				}
				else System.out.println("The file does not contain any function definitions!");
			} catch (FileNotFoundException e) {
				System.out.println("Can't open file!");
			}
		}
		return "";
	}
	
	/**
	 * Import functions from String
	 * @param functionDefinitions the String represents function definitions
	 */
	public void define(String functionDefinitions) {
		StringReader reader = new StringReader(functionDefinitions);
		parser = new Parser(reader);
		try{ 
			parser.parseFunl();
		} catch (RuntimeException e) {
			System.out.println("Function loading error!");
		}
		Stack<Tree<Token>> stack = parser.getStack();
		if(stack == null) System.out.println("Parsing function failed");
		else {
			for(int i = 0; i < stack.size(); i++) {
				Tree<Token> tree = stack.get(i);
				String key = tree.getChildren().get(0).getValue().getValue();
				functionTrees.put(key, tree);
			}
		}
	}
	
	/**
	 * Evaluate an expression
	 * @param input String representing input
	 * @return String representing evaluation result
	 */
	public String evaluateInput(String input) {
		Tree<Token> evalResult = null; 
		try {
			evalResult = parseExps(input); 
		} catch(RuntimeException e) {
			System.out.println("Expression parsing error!");
		}
		Tree<Token> result = null;
		try {
			result = eval(evalResult);
		} catch(RuntimeException e) {
			System.out.println("Expression evaluating error!");
		}
		if(result != null) return result.getValue().getValue();
		return "";
	}
	
	/**
	 * Parse expressions
	 * @param String representing expressions
	 * @return Tree representing expressions
	 */
	public Tree<Token> parseExps(String expS) {
		StringReader reader = new StringReader(expS);
		Parser expParser = new Parser(reader);
		expParser.parseExps();
		return expParser.getStack().get(0);
	}

	/**
	 * Evaluate the expressions 
	 * @param Tree representing expressions 
	 * @return simpler Tree representing expressions
	 */
	public Tree<Token> eval(Tree<Token> expr) {
		if(expr.getChildren().size() == 0) {
			return evalCell(expr);
		}
		if("+-*/".contains(expr.getValue().getValue())) {
			return evalArith(expr);
		} else if(expr.getValue().getValue().equals("val")) {
			return evalVar(expr);
		} else if(expr.getValue().getValue().equals("read")) {
			return evalRead(expr);
		} else if(expr.getValue().getValue().equals("if")) {
			return evalIf(expr);
		} else if(expr.getValue().getValue().equals("$seq")) {
			return evalSeq(expr);
		} else if(expr.getValue().getValue().equals("$call")) {
			return evalFunc(expr);
		}
		return new Tree<Token>(new Token(TokenType.EOL,""));
	}
	
	/**
	 * Evaluate functions
	 * @param expr Tree representing functions
	 * @return simpler Tree representing functions
	 */
	public Tree<Token> evalFunc(Tree<Token> expr) {
		if(funcScope.size() > 100) {
			System.out.println("Recursion limit exceeded!");
			return null;
		}
		HashMap<String, Tree<Token>> currScope = new HashMap<String, Tree<Token>>();
		
		if(!functionTrees.containsKey(expr.getChildren().get(0).getValue().getValue())) {
			if(funcScope.peek().containsKey(expr.getChildren().get(0).getValue().getValue())) {
				Tree<Token> funName = funcScope.peek().get(expr.getChildren().get(0).getValue().getValue());
				Tree<Token> newExpr = new Tree<Token>(expr);
				newExpr.getChildren().set(0, funName);			
				return eval(newExpr);
			}
			System.out.println("The function " + 
					expr.getChildren().get(0).getValue().getValue() + " has not been defined!");
			return null;
		}
		String nameF = expr.getChildren().get(0).getValue().getValue();
		Tree<Token> proto = functionTrees.get(expr.getChildren().get(0).getValue().getValue());
		ArrayList<Tree<Token>> paras = proto.getChildren().get(1).getChildren();
		int paraNum = paras.size();
		ArrayList<Tree<Token>> actualParas = expr.getChildren().get(1).getChildren();
		int actualParaNum = actualParas.size();
		if(paraNum != actualParaNum) {
			System.out.println("The function " + 
					expr.getChildren().get(0).getValue().getValue() + " does not take" + actualParaNum + " parameters!");
			return null;
		}
		for(int i = 0; i < paraNum; i++) {
			Tree<Token> test = eval(actualParas.get(i));
			currScope.put(paras.get(i).getValue().getValue(), test);
		}
		funcScope.push(currScope);
		Tree<Token> result = eval(proto.getChildren().get(2));
		funcScope.pop();
		return result;
	}
	
	/**
	 * Evaluate sequences
	 * @param expr Tree representing sequences
	 * @return simpler Tree representing sequences
	 */
	public Tree<Token> evalSeq(Tree<Token> expr) {
		int index = expr.getChildren().size() - 1;
		for(int i = 0; i < index; i++) {
			evalChild(i, expr);
		}
		return evalChild(index, expr);
	}
	
	/**
	 * Evaluate if statement
	 * @param expr Tree representing if statement
	 * @return simpler Tree representing statement
	 */
	public Tree<Token> evalIf(Tree<Token> expr) {
		Tree<Token> condition = evalChild(0, expr);
		double con = 0.0;
		if(condition.getValue().getType() == TokenType.NUMBER) {
			con = Double.parseDouble(condition.getValue().getValue());
			return (con > 0)? evalChild(1, expr) : evalChild(2, expr);
		}
		System.out.println("The condition of if statement can't be evaluated!");
		return null;
	}
	
	/**
	 * Evaluate read statement
	 * @param expr Tree representing read statement
	 * @return simpler Tree representing read statement
	 */
	public Tree<Token> evalRead(Tree<Token> expr) {
		System.out.print(expr.getChildren().get(0).getValue().getValue() + " ");
		String input = scanner.nextLine();
		return eval(parseExps(input));
	}
	
	/**
	 * Evaluate assignment
	 * @param expr Tree representing assignment
	 * @return simpler Tree representing assignment
	 */
	public Tree<Token> evalVar(Tree<Token> expr) {
		String varName = expr.getChildren().get(0).getValue().getValue();
		Tree<Token> varTree = evalChild(1, expr);
		if(funcScope.size() == 0) REPLScope.put(varName, varTree);
		else funcScope.peek().put(varName, varTree);
		return varTree;
	}
		
	/**
	 * Evaluate arithmetic
	 * @param expr Tree representing arithmetic
	 * @return simpler Tree representing arithmetic
	 */
	public Tree<Token> evalArith(Tree<Token> expr) {
		String op = expr.getValue().getValue();
		Tree<Token> fir = evalChild(0, expr);
		Tree<Token> sec = evalChild(1, expr);
		double f = Double.parseDouble(fir.getValue().getValue());
		double s = Double.parseDouble(sec.getValue().getValue());
		if(op.equals("+")) {
			return new Tree<Token>(new Token(TokenType.NUMBER, (f + s) + ""));
		} else if(op.equals("-")) {
			return new Tree<Token>(new Token(TokenType.NUMBER, (f - s) + ""));
		} else if(op.equals("*")) {
			return new Tree<Token>(new Token(TokenType.NUMBER, (f * s) + ""));
		} else {
			if(s == 0) {
				System.out.println("Divided by 0!");
				return null;
			}
			return new Tree<Token>(new Token(TokenType.NUMBER, (f / s) + ""));
		}
	}
		
	/**
	 * Evaluate cell
	 * @param expr Tree representing cell
	 * @return simpler Tree representing cell
	 */
	public Tree<Token> evalCell(Tree<Token> cell) {
		String s = cell.getValue().getValue();
		double value = 0.0;
		try{
			value = Double.parseDouble(s);
			return new Tree<Token>(new Token(TokenType.NUMBER, value + ""));
		} catch (NumberFormatException e) {
			if(funcScope.size() != 0 && funcScope.peek().containsKey(s)) return funcScope.peek().get(s);
			if(REPLScope.size() != 0 && REPLScope.containsKey(s)) return REPLScope.get(s);
			return new Tree<Token>(new Token(TokenType.NAME, s));
		}
	}
	

	/**
	 * Evaluate children
	 * @param expr Tree representing children
	 * @return simpler Tree representing children
	 */
	public Tree<Token> evalChild(int index, Tree<Token> parent) {
		int size = parent.getChildren().size();
		if(parent.getChildren().size() == 0 || index >= parent.getChildren().size()) {
			System.out.println("Evaluating error!");
			return null;
		} else {
			return eval(parent.getChildren().get(index));
		}
	}
}
