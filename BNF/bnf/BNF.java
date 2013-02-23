/**
 * 
 */
package bnf;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author machao
 * @version 1.0
 */
public class BNF {
	private HashMap<Token, Tree<Token>> rules = new HashMap<Token, Tree<Token>>();
	private Stack<Tree<Token>> stack = new Stack<Tree<Token>>();
	private BnfTokenizer tokenizer = null;
	
	/**
	 * Read in any number of rules to this grammar
	 * @param reader the stream to be read in
	 */
	public void read(Reader reader) {
		tokenizer = new BnfTokenizer(reader);
	}
	
	/**
	 * Writes out the entire grammar
	 * @param writer the stream to be written out to
	 */
	public void write(Writer writer) {
		parseBnf();
		for(Token t : rules.keySet()) {
			System.out.println(t.getValue());
			rules.get(t).print();
			System.out.println("++++++++++++");
		}
		//for(Token t : rules.keySet()) {
			//rules.get(t).print();
		//}
	}
	
	
	public Tree<Token> lookUp(String nonterminal) {
		return null;
	}
	
	/**
	 * Parse the bnf read in
	 * @return true if succeed
	 */
	private boolean parseBnf() {
		while(tokenizer.hasNext()) {
			while(tokenizer.hasNext() && tokenizer.next().getValue().equals("\n")){}
			if(tokenizer.hasNext()){
				tokenizer.back();
				if(!parseRule()) throw new RuntimeException("rule parsing failed!");
			}
		}
		return true;
	}
	
	/**
	 * Parse a single rule
	 * @return true if succeed
	 */
	private boolean parseRule() {
		if(parseNonterminal()) {
			tokenizer.back();
			Token key = tokenizer.next();//get the key of the rule for later use
			Token meta = tokenizer.next();
			if(meta.getType() == Token.TokenType.METASYMBOL && meta.getValue().equals("::=")) {
				if(parseDefinition()) {
					Token next = tokenizer.next();
					int orCount = 0;
					while(next.getType() == Token.TokenType.METASYMBOL && next.getValue().equals("|")) {
						parseDefinition();
						next = tokenizer.next();
						orCount++;
					}
					if(next.getType() == Token.TokenType.METASYMBOL && next.getValue().equals(".")) {
						if(orCount != 0) {
							Tree<Token> firstNode = new Tree<Token>(new Token(Token.TokenType.OR, "OR"));
							for(int i = 0; i <= orCount; i++) {
								firstNode.addChild(stack.pop());
							}
							stack.push(firstNode);//put the rule on the stack
							rules.put(key, firstNode);//put the rule into rule set
						} else {
							rules.put(key, stack.peek());
						}
						return true;
					} else {
						throw new RuntimeException("period symbol parsing failed in rules!");
					}
				}
				throw new RuntimeException("definition parsing failed in rules!");
			}
			throw new RuntimeException("definition symbol parsing failed in rules!");
		}
		throw new RuntimeException("nonterminal parsing failed in rules!");
	}
	
	/**
	 * Parse a single nonterminal
	 * @return
	 */
	private boolean parseNonterminal() {
		Token token = tokenizer.next();
		if(token.getType() == Token.TokenType.NONTERMINAL) {
			Tree<Token> node = new Tree<Token>(token);
			stack.push(node);
			return true;
		} else {
			throw new RuntimeException("nonterminal parsing failed!");
		}
	}
	
	/**
	 * Parse single definition
	 * @return true if succeed
	 */
	private boolean parseDefinition() {
		Token next = tokenizer.next();
		int termCount = 0;
		while(next.getType() != Token.TokenType.METASYMBOL || next.getValue().equals("{") || next.getValue().equals("[")){
			tokenizer.back();
			parseTerm();
			termCount++;
			next = tokenizer.next();
		}
		tokenizer.back();
		if(termCount > 1) {
			Tree<Token> firstNode = new Tree<Token>(new Token(Token.TokenType.SEQUENCE, "SEQUENCE"));
			for(int i = 0; i < termCount; i++) {
				firstNode.addChild(stack.pop());
			}
			stack.push(firstNode);
		}
		return true;
	}
	
	/**
	 * Parse single term
	 * @return true if succeed
	 */
	private boolean parseTerm() {
		Token next = tokenizer.next();
		if(next.getType() == Token.TokenType.TERMINAL) {
			tokenizer.back();
			parseTerminal();
		} else if(next.getType() == Token.TokenType.NONTERMINAL) {
			tokenizer.back();
			parseNonterminal();
		} else if(next.getType() == Token.TokenType.METASYMBOL) {
			if(next.getValue().equals("[")) {
				tokenizer.back();
				parseOptional();
			} else if(next.getValue().equals("{")) {
				tokenizer.back();
				parseAnyNum();
			}
		}
		return true;
	}
	
	/**
	 * Parse single terminal
	 * @return true if succeed
	 */
	private boolean parseTerminal() {
		Token next = tokenizer.next();
		if(next.getType() == Token.TokenType.TERMINAL) {
			Tree<Token> node = new Tree<Token>(next);
			stack.push(node);
			return true;
		} else {
			throw new RuntimeException("parse terminal failed!");
		}
	}
	
	/**
	 * Parse single optional
	 * @return true if succeed
	 */
	private boolean parseOptional() {
		Token next = tokenizer.next();
		if(next.getType() == Token.TokenType.METASYMBOL && next.getValue().equals("[")) {
			if(parseDefinition()) {
				Tree<Token> firstNode = new Tree<Token>(new Token(Token.TokenType.OPTION, "OPTION"));
				firstNode.addChild(stack.pop());
				stack.push(firstNode);
				next = tokenizer.next();
				if(next.getType() != Token.TokenType.METASYMBOL || !next.getValue().equals("]")) {
					throw new RuntimeException("parse ] failed in optional!");
				}
				return true;
			}
			throw new RuntimeException("parse definition failed in optional!");
		}
		throw new RuntimeException("parse [ failed in optional!");
	}
	
	/**
	 * Parse single any number
	 * @return true if succeed
	 */
	private boolean parseAnyNum() {
		Token next = tokenizer.next();
		if(next.getType() == Token.TokenType.METASYMBOL && next.getValue().equals("{")) {
			if(parseDefinition()) {
				Tree<Token> firstNode = new Tree<Token>(new Token(Token.TokenType.ANYNUM, "ANYNUM"));
				firstNode.addChild(stack.pop());
				stack.push(firstNode);
				next = tokenizer.next();
				if(next.getType() != Token.TokenType.METASYMBOL || !next.getValue().equals("}")) {
					throw new RuntimeException("parse } failed in anynum!");
				}
				return true;
			}
			throw new RuntimeException("parse definition failed in anynum!");
		}
		throw new RuntimeException("parse { failed in anynum!");
	}
	
	class Tree<T> {
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
		
		public void print() {
			System.out.println(value.toString());
			for(Tree<T> child : children) {
				child.print();
			}
		}
	} 
}
