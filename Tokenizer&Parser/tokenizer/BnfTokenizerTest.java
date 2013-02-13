package tokenizer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

public class BnfTokenizerTest {

	private Reader reader = null;
	private BnfTokenizer tokenizer = null;
	
	@Before
	public void setUp() throws FileNotFoundException{
		File file = new File("tokenizer/sampleBNF");
		this.reader = new FileReader(file);
		this.tokenizer = new BnfTokenizer(this.reader);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBnfTokenizer() {
		new BnfTokenizer(null);
	}

	@Test
	public void testHasNext() {
		assertTrue(this.tokenizer.hasNext());
		for(int i = 0; i < 300; i++) {
			try {
				this.tokenizer.getChar();
			} catch (IOException e) {
				System.err.println(e.toString());
			}
		}
		assertFalse(this.tokenizer.hasNext());
	}

	@Test
	public void testNext() {

	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemove() {
		this.tokenizer.remove();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testBack() {
		this.tokenizer.back();
	}

	@Test
	public void testPeekChar() {
		try {
			assertEquals('<', this.tokenizer.peekChar());
			assertEquals('<', this.tokenizer.getChar());
			assertEquals('B', this.tokenizer.peekChar());
			assertEquals('B', this.tokenizer.peekChar());
			assertEquals('B', this.tokenizer.peekChar());
			assertEquals('B', this.tokenizer.getChar());
			assertFalse('B'== this.tokenizer.peekChar());
		} catch (IOException e) {
			System.err.println("ioexception");
		}
		try {
			this.reader.close();
		} catch (IOException e) {
			System.err.println("Can't close the stream.");
		}
	}
	
	@Test
	public void testGetChar() {
		try {
			assertEquals('<', this.tokenizer.peekChar());
			assertEquals('<', this.tokenizer.getChar());
			assertEquals('B', this.tokenizer.getChar());
			assertEquals('N', this.tokenizer.getChar());
			assertEquals('F', this.tokenizer.peekChar());
			assertEquals('F', this.tokenizer.peekChar());
			assertFalse('>' == this.tokenizer.getChar());
			assertEquals('>', this.tokenizer.getChar());
		} catch (IOException e) {
			System.err.println("ioexception");
		}
		for(int i = 0; i < 300; i++) {
			try {
				this.tokenizer.getChar();
			} catch (IOException e) {
				System.err.println(e.toString());
			}
		}
		try {
			assertEquals(-1, this.tokenizer.peekChar());
			this.reader.close();
		} catch (IOException e) {
			System.err.println("Can't close the stream.");
		}
	}
}
