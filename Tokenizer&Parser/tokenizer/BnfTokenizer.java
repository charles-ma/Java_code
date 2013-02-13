/**
 * 
 */
package tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;

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
		this.isVirgin = false;
		return null;
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
