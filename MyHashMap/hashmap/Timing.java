/**
 * 
 */
package hashmap;

import java.util.HashMap;
import java.util.Random;

/**
 * In charge of the timing tests for different kinds of hashmaps
 * @author machao
 * @version 1.0
 */
public class Timing {

	/**
	 * Main function to start the tests
	 * @param args system parameter
	 */
	public static void main(String[] args) {
		ListHashMap<Integer, Integer> listMap = new ListHashMap<Integer, Integer>(100);
		TreeHashMap<Integer, Integer> treeMap = new TreeHashMap<Integer, Integer>(100);
		HashMap<Integer, Integer> jMap = new HashMap<Integer, Integer>(100);
		long start = 0, end = 0;
		Random ran = new Random();
		int ranNum = 0;
		int factor = 1000000;
		int a[] = new int[factor * 5];
		for(int j = 0; j < factor * 5; j++) {
			a[j] = ran.nextInt(100);
		}
		for(int i = 1; i <= 5; i++) {
			listMap.clear();
			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				listMap.put(a[k], a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the put method of ListHashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");

			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				listMap.get(a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the get method of ListHashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");
			
		}
		
		System.out.println();
		
		for(int i = 1; i <= 5; i++) {
			treeMap.clear();
			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				treeMap.put(a[k], a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the put method of TreeHashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");

			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				treeMap.get(a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the get method of TreeHashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");
		
		}
		System.out.println();
		
		for(int i = 1; i <= 5; i++) {
			jMap.clear();
			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				jMap.put(a[k], a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the put method of Java HashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");

			start = System.nanoTime();
			for(int k = 0; k < factor * i; k++) {
				jMap.get(a[k]);
			}
			end = System.nanoTime();
			if(i != 1) System.out.println("The time of the get method of Java HashMap repeated " + factor * i + " times is " + (end - start) + " nano secs");
			
		}
		
	}

}
