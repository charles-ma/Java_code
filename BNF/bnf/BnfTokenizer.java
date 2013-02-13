/**
 * 
 */
package bnf;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;

import bnf.Token.TokenType;

/**
 * This class can tokenize the input stream
 * @author machao
 * @version 1.0
 */
public class BnfTokenizer implements Iterator<Token> {

	private Reader reader = null;
	private Token preToken = null;
	private boolean isBack = false;
	private boolean isVirgin = true;
	private LinkedList<Integer> peekedNotRead = new LinkedList<Integer> ();
	private enum State {START, META, METAORTERM, METAORTERM2, NONTERM, FINISHED, ERROR, ESCAPE, ESCAPEDEF, ESCAPEDEF2, TERM};
	private State state = State.START;
	/**
	 * Constructor of the BnfTokenizer
	 * @param reader the stream to be tokenized
	 */
	public BnfTokenizer(Reader reader) {
		if(reader == null) throw new IllegalArgumentException();
		this.reader = reader;
	}
		
	@Override
	public boolean hasNext() {
		if(this.isBack) return true;
		try {
			if(peekChar() == -1) return false;
		} catch (IOException e) {
			System.err.println("Something wrong while reading!");
			return false;
		}
		return true;
	}

	@Override
	public Token next() {
		if(!hasNext()) throw new UnsupportedOperationException("Don't have any tokens left!");
		if(this.isBack) {
			this.isBack = false;
			return this.preToken;
		}
		StringBuffer value = new StringBuffer();
		this.state = State.START;
		this.isVirgin = false;
		while(true) {
			char currChar = ' ';
			try {
				currChar = getChar();				
			} catch (IOException e) {
				throw new UnsupportedOperationException("Don't have any tokens left!");
			}
			switch(this.state) {
				case START:
					if(currChar == '<') {
						this.state = State.NONTERM;
						value.append(currChar);
					} else if(currChar == '|' || currChar == '[' || currChar == ']' || currChar == '{' || currChar == '}' || currChar == '.') {
						this.state = State.META;
						value.append(currChar);
						return new Token(TokenType.METASYMBOL, value.toString());
					} else if(currChar == ':') {
						this.state = State.METAORTERM;
						value.append(currChar);
					} else if(currChar == ' ' || currChar == '\n') {
						this.state = State.START;
					} else if(currChar == '\\') {
						this.state = State.ESCAPE;
					} else {
						this.state = State.TERM;
						value.append(currChar);
					}
					break;
				case META:
					break;
				case METAORTERM:
					if(currChar == ':') {
						this.state = State.METAORTERM2;
					} else {
						this.state = State.TERM;
					}
					value.append(currChar);
					break;
				case METAORTERM2:
					if(currChar == '=') {
						this.state = State.FINISHED;
						value.append(currChar);
						return new Token(TokenType.METASYMBOL, value.toString());
					} else {
						this.state = State.TERM;
						value.append(currChar);
					}
					break;
				case NONTERM:
					if(currChar == '>') {
						this.state = State.FINISHED;
						value.append(currChar);
						return new Token(TokenType.NONTERMINAL, value.toString());
					} else if(currChar == '<') {
						this.state = State.ERROR;
						value.append(currChar);
					} else {
						value.append(currChar);
					}
					break;
				case TERM:
					if(currChar == '\\') {
						this.state = State.ESCAPE;
					} else if(currChar == ':') {
						value.append(currChar);
						this.state = State.METAORTERM;
					} else if(currChar == '<' || currChar == '>' || currChar == '|' || currChar == '[' || currChar == ']' || currChar == '{' || currChar == '}' || currChar == '.') {
						this.state = State.FINISHED;
						int ascii = (int)currChar;
						this.peekedNotRead.add(ascii);
						return new Token(TokenType.TERMINAL, value.toString());
					}
					break;
				case ESCAPE:
					if(currChar == ':') {
						this.state = State.ESCAPEDEF;
						value.append(currChar);
					} else if(currChar == 'n') {
						this.state = State.TERM;
						value.append('\n');
					} else if(currChar == 't') {
						this.state = State.TERM;
						value.append('\t');
					} else if(currChar == 'r') {
						this.state = State.TERM;
						value.append('\r');
					} else {
						this.state = State.TERM;
						value.append(currChar);
					}
					break;
				case ESCAPEDEF:
					if(currChar == ':') {
						value.append(currChar);
						this.state = State.ESCAPEDEF2;
					} else {
						value.append(currChar);
						this.state = State.TERM;
					}
					break;
				case ESCAPEDEF2:
					if(currChar == '=') {
						value.append(currChar);
						this.state = State.FINISHED;
						return new Token(TokenType.METASYMBOL, value.toString());
					} else {
						value.append(currChar);
						this.state = State.TERM;
					}
					break;
				case ERROR:
					throw new RuntimeException("Symentic fault!");
				case FINISHED:
					break;
			}
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("You can't remove!");
	}
	
	/**
	 * Resets the tokenizer so that the next call to next() will return the same token as the previous call to next()
	 */
	public void back() {
		if(this.isVirgin) throw new UnsupportedOperationException("You haven't tokenized anything yet!");
		this.isBack = true;
	}

	/**
	 * Peeks the next character in the input stream
	 * @return ascii value of the character (-1 if reaches end of the stream)
	 * @throws IOException
	 */
	public int peekChar() throws IOException{
		if(this.peekedNotRead.size() != 0) return this.peekedNotRead.peek();
		int asciiPeeked = this.reader.read();
		this.peekedNotRead.add(asciiPeeked);
		return asciiPeeked;
	}
	
	/**
	 * Gets the next character in the input stream
	 * @return the character
	 * @throws IOException
	 */
	public char getChar() throws IOException{
		if(peekChar() == -1) throw new IOException("No more characters!");
		if(this.peekedNotRead.size() != 0) {
			int ascii = this.peekedNotRead.remove();
			return (char)ascii;
		} else {
			return (char)this.reader.read();
		}
	}
}
