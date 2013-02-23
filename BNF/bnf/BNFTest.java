package bnf;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

public class BNFTest {

	private Reader reader = null;
	private BNF bnf = new BNF();

	@Test
	public void testRead() {
		StringReader reader = new StringReader("");
		bnf.read(reader);
		bnf.write(new StringWriter());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testWrite() {
		StringReader reader = new StringReader("<program> ::= {<line>;}.");
		StringWriter writer = new StringWriter();
		bnf.read(reader);
		bnf.write(writer);
		assertEquals("<program>::={<line>;}.\n", writer.toString());
		bnf.write(null);
	}

	@Test
	public void testLookUp() {
		File file = new File("bnf/Grammar.txt");
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bnf.read(reader);
		assertTrue(bnf.lookUp("program") != null);
		assertTrue(bnf.lookUp("<program>") != null);
		bnf.lookUp("program").print();
		assertTrue(bnf.lookUp("line") != null);
		assertTrue(bnf.lookUp("<line>") != null);
		bnf.lookUp("line").print();
		assertTrue(bnf.lookUp("readFile") != null);
		assertTrue(bnf.lookUp("<readFile>") != null);
		bnf.lookUp("readFile").print();
	}
}
