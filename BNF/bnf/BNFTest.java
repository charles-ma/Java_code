package bnf;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

public class BNFTest {

	private Reader reader = null;
	private BNF bnf = new BNF();
	
	@Before
	public void setUp() throws FileNotFoundException {
		File file = new File("bnf/Grammar.txt");
		reader = new FileReader(file);
		bnf.read(reader);
	}
	
	@Test
	public void testRead() {
		
	}

	@Test
	public void testWrite() {
		
	}

	@Test
	public void testLookUp() {
		bnf.write(null);
	}

}
