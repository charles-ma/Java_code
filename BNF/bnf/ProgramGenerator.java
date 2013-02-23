/**
 * 
 */
package bnf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;

import bnf.BNF.Tree;

/**
 * @author machao
 * @version 1.0
 */
public class ProgramGenerator {

	private File file = null;
	private static BNF bnf = new BNF();
	private Reader reader = null;
	private StringWriter writer = new StringWriter();
	
	/**
	 * Constructor of this class
	 */
	public ProgramGenerator(String fileName) {
		if(fileName != null) {
			file = new File(fileName);
		} else {
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showOpenDialog(null);
			if(option == chooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
			} else {
				System.exit(0);
			}
		} 
		
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bnf.read(reader);
		bnf.write(writer);
		System.out.print("The Grammar is:\n" + writer.toString() + "----------------------\n");
		for(int i = 1; i <= 3; i++) {
			System.out.println("Sample Program " + i + " :");
			printList(expand("program"));
			System.out.println("----------------------");
		}
	}
	
	/**
	 * Main function of the program
	 * @param args startup parameters
	 */
	public static void main(String[] args) {
		if(args.length == 0) new ProgramGenerator(null);
		else new ProgramGenerator(args[0]);
	}

	/**
	 * Given a terminal or a nonterminal, expands it into a List of terminals
	 * @param term term to be expanded
	 * @return the expanding result
	 */
	public static List<String> expand(String term) {
		if(term == null) return null;
		BNF.Tree<Token> rule = bnf.lookUp(term);
		if(rule != null) return expand(rule);
		return null;
	}

	/**
	 * Given a token, expands it into a List of terminals
	 * @param rule the tree of which the value is the token to be expanded
	 * @return the expanding result
	 */
	public static List<String> expand(BNF.Tree<Token> rule) {
		ArrayList<String> result = new ArrayList<String>();
		if(rule == null) return null;
		Token value = rule.getValue();
		Random rand = new Random();
		ArrayList<Tree<Token>> subtrees = rule.getChildren();
		for(int i = subtrees.size() - 1; i >= 0; i--) {
			Tree<Token> subtree = subtrees.get(i);
			if(value.getType() == Token.TokenType.SEQUENCE) {
				result.addAll(expand(subtree));
			} else if(value.getType() == Token.TokenType.ANYNUM) {
				int num = rand.nextInt(10);
				for(int j = 0; j < num; j++) {
					result.addAll(expand(subtree));
				}
			} else if(value.getType() == Token.TokenType.OPTION) {
				int num = rand.nextInt(2);
				for(int j = 0; j < num; j++) {
					result.addAll(expand(subtree));
				}
			} else if(value.getType() == Token.TokenType.OR) {
				int num = subtrees.size();
				num = rand.nextInt(num);
				result.addAll(expand(subtrees.get(num)));
				break;
			} else {
				
			}
		}
		if(value.getType() == Token.TokenType.NONTERMINAL) {
			result.addAll(expand(value.getValue()));
		} else if(value.getType() == Token.TokenType.TERMINAL) {
			result.add(value.getValue());
		}
		return result;
	}
	
	/**
	 * Print out the strings (which should all be terminals), separated by spaces
	 * @param list the list to be printed out
	 */
	public static void printList(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			if(!list.get(i).equals(";")) System.out.print(list.get(i) + " ");
			else System.out.print(list.get(i) + "\n");
		}
	}
}
