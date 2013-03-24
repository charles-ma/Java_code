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
	
	/**
	 * The starting point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		new Funl().runREPL();
	}
	
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
			}
		}
	}
	
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
	
	public void define(String functionDefinitions) {
		StringReader reader = new StringReader(functionDefinitions);
		parser = new Parser(reader);
		parser.parseFunl();
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
	
	public String evaluateInput(String input) {
		//change input into Tree<Token>
		Tree<Token> evalResult = null; 
		return "evaluating!";
	}

	public Tree<Token> eval(Tree<Token> expr) {
		return new Tree<Token>(new Token(TokenType.EOI,""));
	}
}
