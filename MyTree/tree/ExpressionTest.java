package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest {

	private Expression exp = new Expression("+ (5 10 -( *(15 20) 25) 30)");
	private Expression exp1 = new Expression("- (10 *(3  /(5 2)))");
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpression() {
		Expression e = new Expression("abd( cdf)");
	}

	@Test
	public void testEvaluate() {
		assertEquals(exp.evaluate(), 320);
		assertEquals(exp1.evaluate(), 4);
	}

	@Test
	public void testToString() {
		//System.out.print(exp.toString());
		assertEquals(exp.toString(), "5+10+((15*20)-25)+30");
		assertEquals(exp1.toString(), "10-(3*(5/2))");
	}

}
