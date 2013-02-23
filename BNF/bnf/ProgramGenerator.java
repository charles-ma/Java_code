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
		System.out.print(writer.toString());
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
		return null;
	}

	/**
	 * Print out the strings (which should all be terminals), separated by spaces
	 * @param list the list to be printed out
	 */
	public static void printList(List<String> list) {
		
	}
}
