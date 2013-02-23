package bnf;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ProgramGeneratorTest {

	@Test
	public void testExpand() {
		ProgramGenerator p = new ProgramGenerator("bnf/Grammar.txt");
		List<String> result = p.expand("fileName");
		assertEquals(1, result.size());
		String s = result.get(0);
		assertTrue(s.equals("a") || s.equals("b") || s.equals("c") || s.equals("d") || s.equals("e"));
		result = p.expand("Num");
		assertEquals(1, result.size());
		s = result.get(0);
		assertTrue(s.equals("0") || s.equals("1")); 
	}
	
}
