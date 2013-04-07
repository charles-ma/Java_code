/**
 * 
 */
package primes;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author charles
 * @version 1.0
 */
public class PrimeCounter {

	public static ForkJoinPool fj = new ForkJoinPool(4);
	private final int SEQUENTIAL_CUTOFF = 5000;
		
	/**
	 * Create multi-thread to calculate the number of primes
	 * @param args program parameters
	 */
	public static void main(String[] args) {
		PrimeCounter pc = new PrimeCounter();
		System.out.println("My computer has 4 cores;");
		System.out.println("Current computer has " + Runtime.getRuntime().availableProcessors() + " cores;");
		System.out.println("---------------------------------------------");
		for (int limit = 1000; limit <= 10000000; limit *= 10) {
			long start = System.nanoTime();
			int n = pc.startCount(1, limit);
            long finish = System.nanoTime();
            System.out.println(n + " primes less than " + limit);
            System.out.println("Time: " + ((finish - start) / 1e9) + " seconds.\n");
        }
        System.out.println("Done.");
	}
	
	/**
	 * Start the counting of prime numbers 
	 * @param start the starting number
	 * @param end the ending number
	 * @return the number of primes between start and end
	 */
	public int startCount(int start, int end) {
		CountPrimeNum cpm = new CountPrimeNum(start, end);
		return fj.invoke(cpm);
	}
	
    /**
     * Count the number of primes less than n
     * @param start the lower bound of the range to be tested 
     * @param end the upper bound of the range to be tested
     * @return the number of primes
     */
    private static int countPrimes(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) count += 1;
        }
        return count;
    }
    
    /**
     * Decides whether a number is prime
     * @param n the number to be tested
     * @return true iff the number if prime
     */
    private static boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
        int limit = (int)(Math.sqrt(n)+ 0.5);
        for (int i = 3; i <= limit; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
	
    /**
     * Class to carry out recursive work with multi-thread
     * @author charles
     * @version 1.0
     */
    class CountPrimeNum extends RecursiveTask<Integer>{
    	
    	private int start = 0, end = 0, depth = 0;

    	/**
    	 * Constructor for CountPrimeNum
    	 * @param start the starting number
    	 * @param end the ending number
    	 */
    	public CountPrimeNum(int start, int end) {
    		this.start = start;
    		this.end = end;
    		this.depth = depth;
    	}
    	    	
		@Override
		protected Integer compute() {
			if(end - start < SEQUENTIAL_CUTOFF) {
				return PrimeCounter.countPrimes(start, end);
			} else {
				int middle = start + (end - start) / 2;
				CountPrimeNum left = new CountPrimeNum(start, middle);
				CountPrimeNum right = new CountPrimeNum(middle + 1, end);
				left.fork();
				int rightRes = right.compute();
				int leftRes = left.join();
				return rightRes + leftRes;
			}
		}
    	
    }
    
}
