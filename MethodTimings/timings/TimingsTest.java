package timings;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;

public class TimingsTest {

    int[] a = Timings.randomArray(10, 100, 1234);
    int[] b = Timings.randomArray(15, 100, 1234);
    int[] c = Timings.randomArray(20, 100, 1234);
    int[] d = Timings.randomArray(25, 100, 1234);

    @Test
	public void testSubset1() {
	Timings.randomize(a);
	Timings.randomize(c);
	assertTrue(Timings.subset1(a, b));
	assertTrue(Timings.subset1(c, d));
    }

    @Test
	public void testSubset2() {
	Arrays.sort(b);
	Arrays.sort(d);
	assertTrue(Timings.subset2(a, b));
	assertTrue(Timings.subset2(c, d));	
    }

    @Test
	public void testSubset3() {
	Timings.randomize(b);
	Timings.randomize(d);
	assertTrue(Timings.subset3(a, b));
	assertTrue(Timings.subset3(c, d));
    }

    @Test
	public void testSubset4() {
	Arrays.sort(a);
	Arrays.sort(b);
	Arrays.sort(c);
	Arrays.sort(d);
	assertTrue(Timings.subset3(a, b));
	assertTrue(Timings.subset3(c, d));
    }

    @Test
	public void testFindSubset() {
	int[] p = Timings.createProblem(1000, 20, 2);
	int[] a = Timings.findSubset(p, 1000);
	int sum = 0;
	assertTrue(Timings.subset1(a, p));
	for(int i = 0; i < a.length; i++) {
	    sum += a[i];
	}
	assertTrue(sum == 1000);

	p = Timings.createProblem(2000, 30, 2);
	a = Timings.findSubset(p, 2000);
	sum = 0;
	assertTrue(Timings.subset1(a, p));
	for(int i = 0; i < a.length; i++) {
	    sum += a[i];
	}
	assertTrue(sum == 2000);

	p = Timings.createProblem(500, 15, 2);
	a = Timings.findSubset(p, 500);
	sum = 0;
	assertTrue(Timings.subset1(a, p));
	for(int i = 0; i < a.length; i++) {
	    sum += a[i];
	}
	assertTrue(sum == 500);
    }

}
