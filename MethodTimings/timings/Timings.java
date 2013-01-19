
/**
 * 
 */
package timings;

import java.util.Arrays;
import java.util.Random;

/**
 * @author machao
 *
 */
public class Timings {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
	Timings timings = new Timings();
    }

    /**
     *Tests whether array sub is a subset of array super, both of them are unsorted arrays
     *@param sub the sub array to be tested
     *@param super the super array to be tested
     *@return a boolean value indicates the result
     */
    public static boolean subset1(int[] sub, int[] sup){
	return false;
    }

    /**
     *Tests whether array sub is a subset of array super, array sub is unsorted, super sorted
     *@param sub the sub array to be tested
     *@param super the super array to be tested
     *@return a boolean value indicates the result
     */
    public static boolean subset2(int[] sub, int[] sup){
	return false;
    }

    /**
     *Tests whether array sub is a subset of array super, array sub is sorted and super unsorted
     *@param sub the sub array to be tested
     *@param super the super array to be tested
     *@return a boolean value indicates the result
     */
    public static boolean subset3(int[] sub, int[] sup){
	return false;
    }

    /**
     *Tests whether array sub is a subset of array super, both of them are sorted
     *@param sub the sub array to be tested
     *@param super the super array to be tested
     *@return a boolean value indicates the result
     */
    public static boolean subset4(int[] sub, int[] sup){
	return false;
    }

    /**
     *Find the return subset of numbers such that the sum of the numbers in the subset is exactly equal to target, assuming that at least one solution exists
     *@param numbers the array to be tested
     *@target the sum
     *@return subset that all its elements add up to target
     */
    public static int[] findSubset(int[] numbers, int target){
	return null;
    }

    /**
     * Creates an array of random numbers in the range 1 to <code>limit</code>.
     * No number will occur more than once in this array, but all calls with
     * the same value of <code>seed</code> will return the same sequence of
     * numbers, regardless of the size of the array.
     * 
     * @param size The size of the array to create.
     * @param limit All returned numbers will be less than or equal to this value.
     * @param seed A seed for the random number generator.
     * @return An array of unique random numbers.
     */
    public static int[] randomArray(int size, int limit, int seed) {
	assert size <= limit;
	java.util.Random rand = new java.util.Random(seed);
	int[] numbers = new int[size];
	outerLoop:
        for (int i = 0; i < numbers.length; i++) {
            int candidate = rand.nextInt(limit) + 1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] == candidate) {
                    i--;
                    continue outerLoop;
                }
            }
            numbers[i] = candidate;
        }
	return numbers;
    }

    /**
     * Randomizes an array in place.
     * 
     * @param array The array to be randomized (shuffled).
     */
    public static void randomize(int[] array) {
	java.util.Random rand = new java.util.Random();
	for (int i = array.length; i > 1; i--) {
	    int choice = rand.nextInt(i);
	    int temp = array[choice];
	    array[choice] = array[i - 1];
	    array[i - 1] = temp;
	}
    }

    /**
     * Divide a given number, <code>sum</code>, into <code>numbers</code>
     * addends, such that the addends sum to the given number, and the
     * addends can be partitioned into <code>piles</code> equal piles.
     * 
     * @param sum The number to be broken into addends.
     * @param numbers The desired number of addends.
     * @param piles The desired number of piles.
     * @return An array of numbers to be sorted into piles.
     */
    public static int[] createProblem(int sum, int numbers, int piles) {
	check(sum >= numbers,
	      "You can't have more numbers than their sum.");
	check(numbers >= piles,
	      "You must have at least as many numbers as piles");
	check(sum % piles == 0,
	      "The sum must be divisible by the number of piles");
	int[] result = new int[numbers];
	// Determine how many numbers to put into each pile (at least 1 each)
	int[] pileCounts;

	result = partitionNumber(sum, numbers, piles);
	randomize(result);
	return result;
    }

    /**
     * Throws an <code>IllegalArgumentException</code> if its boolean
     * input parameter is <code>false</code>.
     * 
     * @param b The parameter to test.
     * @param string The message to include in the <code>IllegalArgumentException</code>.
     */
    private static void check(boolean b, String string) {
	if (b) return;
	throw new IllegalArgumentException(string);
    }

    /**
     * Separates a positive number into an array of positive numbers
     * (addends) which, when added, will yield the original number.
     * 
     * @param total The number to which the addends should sum.
     * @param numberOfNumbers How many addends are desired.
     * @param piles The number of equal piles that should be created.
     * @return The array of numbers that sum to <code>sum</code>.
     */
    static int[] partitionNumber(int total, int numberOfNumbers, int piles) {
	int[] result = new int[numberOfNumbers];
	int[] cuts = new int[numberOfNumbers];
	Random rand = new Random();//added by Chao Ma
	
	// Insert initial cuts to make equal piles
	for (int i = 0; i < piles; i++) {
	    cuts[i] = (i + 1) * (total / piles);
	}
	// Insert remaining cuts
	for (int i = piles; i < numberOfNumbers; i++) {
	    // Choose each cut to be 0 < cut < sum
	    cuts[i] = rand.nextInt(total - 1) + 1;
	    // Ensure that the new cut is not the same as an existing cut
	    for (int j = 0; j < i; j++) {
		if (cuts[j] == cuts[i]) i--;
	    }
	}
	// Transform cuts into ranges
	Arrays.sort(cuts);
	result[0] = cuts[0];
	for (int i = 1; i < numberOfNumbers; i++) {
	    result[i] = cuts[i] - cuts[i - 1];
	}
	return result;
    }
}
