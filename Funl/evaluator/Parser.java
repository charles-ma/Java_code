/**
 * 
 */
package evaluator;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

/**
 * The parser for funl
 * @author machao
 * @version 1.0
 */
public class Parser {
	private Tokenizer tokenizer = null;
	private Stack<Tree<Token>> stack = null;
	
	public static void main(String[] args) {
		try {
			new Parser(new FileReader("./evaluator/sample")).parseFunl();
		} catch (IOException e) {
			System.out.println("file!");
		}
	}
	
	/**
	 * Constructor
	 * @param reader input
	 */
	public Parser(Reader reader) {
		tokenizer = new Tokenizer(reader);
		stack = new Stack<Tree<Token>>();
	}
	
	/**
	 * Parse funl
	 * @return true if succeed
	 */
	public boolean parseFunl() {
		while(tokenizer.next().getType() != TokenType.EOI) {
			tokenizer.pushBack();
			if(tokenizer.next().getType() != TokenType.EOL) {
				tokenizer.pushBack();
				parseFunDef();
			}
		}
		return true;
	}
	
	/**
	 * Parse function definitions
	 * @return true if succeed
	 */
	public boolean parseFunDef() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.KEYWORD && next.getValue().equals("def")) {
			Tree<Token> tree = new Tree<Token>(next);
			parseName();
			tree.addChild(stack.pop());
			next = tokenizer.next();
			Tree<Token> paras = new Tree<Token>(new Token(TokenType.NAME, "$seq"));
			while(next.getType() != TokenType.SYMBOL || !next.getValue().equals("=")) {
				tokenizer.pushBack();
				parsePara();
				paras.addChild(stack.pop());				
				next = tokenizer.next();
			}
			tree.addChild(paras);
			parseExps();
			tree.addChild(stack.pop());
			next = tokenizer.next();
			if(next.getType() != TokenType.KEYWORD || !next.getValue().equals("end")) throw new RuntimeException("Failed parsing keyword end!");
			stack.push(tree);
		} else {
			throw new RuntimeException("Failed parsing keyword def!");
		}
		return true;
	}
		
	/**
	 * Parse name
	 * @return true if succeed
	 */
	public boolean parseName() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.NAME) {
			Tree<Token> tree = new Tree<Token>(next);
			stack.push(tree);
		} else {
			throw new RuntimeException("Failed parsing name!");
		}
		return true;
	}
	
	/**
	 * Parse parameters
	 * @return true if succeed
	 */
	public boolean parsePara() {
		parseName();
		return true;		
	}
	
	/**
	 * Parse expressions
	 * @return true if succeed
	 */
	public boolean parseExps() {
		Token next = null;
		Tree<Token> tree = new Tree<Token>(new Token(TokenType.NAME, "$seq"));
		do {
			parseExp();
			tree.addChild(stack.pop());
			next = tokenizer.next();
			while(next.getType() == TokenType.EOL) {
				next = tokenizer.next();
			}
		} while(next.getType() == TokenType.SYMBOL && next.getValue().equals(","));
		stack.push(tree);
		tokenizer.pushBack();
		return true;
	}
	
	/**
	 * Parse expression
	 * @return true if succeed
	 */
	public boolean parseExp() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.KEYWORD && next.getValue().equals("val")) {
			tokenizer.pushBack();
			parseValDef();
		} else {
			tokenizer.pushBack();
			parseTerm();
			while(parseAdd()) {
				parseTerm();
				Tree<Token> sub = stack.pop();
				Tree<Token> tree = stack.pop();
				tree.addChild(stack.pop());
				tree.addChild(sub);
				stack.push(tree);
			}
			tokenizer.pushBack();
		}
		return true;
	}
	
	/**
	 * Parse variable definition
	 * @return true if succeed
	 */
	public boolean parseValDef() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.KEYWORD && next.getValue().equals("val")) {
			Tree<Token> tree = new Tree<Token>(next);
			parseName();
			tree.addChild(stack.pop());
			next = tokenizer.next();
			if(next.getType() == TokenType.SYMBOL && next.getValue().equals("=")) {
				parseExp();
				tree.addChild(stack.pop());
				stack.push(tree);
			} else {
				throw new RuntimeException("Failed parsing = !");
			}
		}
		return true;
	}
	
	/**
	 * Parse term
	 * @return true if succeed
	 */
	public boolean parseTerm() {
		parseFactor();
		while(parseMul()) {
			parseFactor();
			Tree<Token> sub = stack.pop();
			Tree<Token> tree = stack.pop();
			tree.addChild(stack.pop());
			tree.addChild(sub);
			stack.push(tree);
		}
		tokenizer.pushBack();
		return true;
	}
	
	/**
	 * Parse +
	 * @return true if succeed
	 */
	public boolean parseAdd() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.SYMBOL && (next.getValue().equals("+") || next.getValue().equals("-"))) {
			Tree<Token> tree = new Tree<Token>(next);
			stack.push(tree);
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * Parse factors
	 * @return true if succeed
	 */
	public boolean parseFactor() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.NAME) {
			tokenizer.pushBack();
			parseName();
			next = tokenizer.next();
			if(next.getType() == TokenType.SYMBOL && next.getValue().equals("(")) {
				next = tokenizer.next();
				Tree<Token> tree = new Tree<Token>(new Token(TokenType.NAME, "$call"));
				if(next.getType() == TokenType.SYMBOL && next.getValue().equals(")")) {
					//tokenizer.pushBack();
					stack.push(new Tree<Token>(new Token(TokenType.NAME, "")));
				} else {
					tokenizer.pushBack();
					parseExps();
					next = tokenizer.next();
					if(next.getType() != TokenType.SYMBOL || !next.getValue().equals(")")) throw new RuntimeException("Failed parsing ) !");
				}
				Tree<Token> sub = stack.pop();
				tree.addChild(stack.pop());
				tree.addChild(sub);
				stack.push(tree);
			} else {
				tokenizer.pushBack();
			}
		} else if(next.getType() == TokenType.KEYWORD) {
			if(next.getValue().equals("if")) {
				Tree<Token> tree = new Tree<Token>(next);
				parseExps();
				tree.addChild(stack.pop());
				next = tokenizer.next();
				if(next.getType() == TokenType.KEYWORD && next.getValue().equals("then")) {
					parseExps();
					tree.addChild(stack.pop());
					next = tokenizer.next();
				} else {
					throw new RuntimeException("Failed parsing key word then in factor!");
				}
				if(next.getType() == TokenType.KEYWORD && next.getValue().equals("else")) {
					parseExps();
					tree.addChild(stack.pop());
					next = tokenizer.next();
				} else {
					throw new RuntimeException("Failed parsing key word else in factor!");
				}
				if(next.getType() == TokenType.KEYWORD && next.getValue().equals("end")) {
					stack.push(tree);
				} else {
					throw new RuntimeException("Failed parsing key word end in factor!");
				}
			} else if(next.getValue().equals("read")) {
				parseQuoteString();
				Tree<Token> tree = new Tree<Token>(next);
				tree.addChild(stack.pop());
				stack.push(tree);
 			} else {
				throw new RuntimeException("Failed parsing key words in factor!");
			}
		} else if(next.getType() == TokenType.NUMBER) {
			tokenizer.pushBack();
			parseNum();
		} else if(next.getType() == TokenType.SYMBOL && next.getValue().equals("(")) {
			parseExp();
			next = tokenizer.next();
			if(next.getType() != TokenType.SYMBOL || !next.getValue().equals(")")) throw new RuntimeException("Failed parsing ) !");
		} else {
			throw new RuntimeException("Failed parsing factor!");
		}
		return true;
	}
	
	/**
	 * Parse *
	 * @return true if succeed
	 */
	public boolean parseMul() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.SYMBOL && (next.getValue().equals("*") || next.getValue().equals("/"))) {
			Tree<Token> tree = new Tree<Token>(next);
			stack.push(tree);
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * Parse quote string
	 * @return true if succeed
	 */
	public boolean parseQuoteString() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.STRING) {
			Tree<Token> tree = new Tree<Token>(next);
			stack.push(tree);
		} else {
			throw new RuntimeException("Failed parsing quoted String!");
		}
		return true;
	}
	
	/**
	 * Parse number
	 * @return true if succeed
	 */
	public boolean parseNum() {
		Token next = tokenizer.next();
		while(next.getType() == TokenType.EOL) {
			next = tokenizer.next();
		}
		if(next.getType() == TokenType.NUMBER) {
			Tree<Token> tree = new Tree<Token>(next);
			stack.push(tree);
		} else {
			throw new RuntimeException("Failed parsing number!");
		}
		return true;
	}
	
	/**
	 * Get the stack
	 * @return the stack
	 */
	public Stack<Tree<Token>> getStack() {
		return stack;
	}
}
