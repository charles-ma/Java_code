/**
 * 
 */
package tokenizer;

import java.io.Reader;
import java.util.Iterator;

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
	
	/**
	 * Constructor of the BnfTokenizer
	 * @param reader the stream to be tokenized
	 */
	public BnfTokenizer(Reader reader) {
		this.reader = reader;
	}
		
	@Override
	public boolean hasNext() {
		if(this.isBack) return true;
		return false;
	}

	@Override
	public Token next() {
		if(!hasNext()) throw new UnsupportedOperationException("Don't have any tokens left!");
		this.isVirgin = false;
		if(this.isBack) {
			this.isBack = false;
			return this.preToken;
		}
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

}
