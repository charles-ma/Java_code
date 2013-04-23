/**
 * 
 */
package regmad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simulate the whole registration madness
 * @author machao
 * @version 1.0
 */
public class RegMad {
	public static final int sNum = 190;
	private RegSys sys = null;
	
	/**
	 * @param args main function parameter
	 */
	public static void main(String[] args) {
		new RegMad().startReg();
	}

	/**
	 * Constructor for RegMad class
	 */
	public RegMad() {
		sys = new RegSys();
	}
	
	/**
	 * Start the registration
	 */
	public void startReg() {
		ExecutorService pool = Executors.newFixedThreadPool(sNum / 3);
		System.out.println("Simu start!");
		long startTime = System.currentTimeMillis();
		for(int i = 1; i <= sNum; i++) {
			Student s = new Student(i, sys);
			pool.execute(s);
		}
		while(!sys.isOver()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		}
		long endTime = System.currentTimeMillis();
		long timePeriod = endTime - startTime;
		System.out.println("Simu end!\n\nStatistics:\n-------------------------------------------------");
		sys.printResult();
		System.out.println("Simu time: " + timePeriod / 1000.0 + " seconds");
		pool.shutdownNow();
		System.exit(0);
	}
	
}
