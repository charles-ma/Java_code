/**
 * 
 */
package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * RSAKeyGenerator
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class RSAKeyGenerator {

	private int a, b, c, m;
		
	/**
	 * Main function for RSAKeyGenerator
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Enter the nth prime and mth prime to compute:");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = inFromUser.readLine();
		} catch (IOException e) {
			System.out.println("Read in error!");
		}
		String[] nums = input.split(" ");
		int fPrime = Integer.parseInt(nums[0]);
		int sPrime = Integer.parseInt(nums[1]);
		new RSAKeyGenerator(fPrime, sPrime).generateKeys();
	}

	/**
	 * Constructor for RSAKeyGenerator
	 * @param firstPrime index of first prime number
	 * @param secondPrime index of second prime number
	 */
	public RSAKeyGenerator(int firstPrime, int secondPrime) {
		if(firstPrime == secondPrime) throw new RuntimeException("Two primes should be different!");
		a = findPrime(firstPrime);
		b = findPrime(secondPrime);
		c = a * b;
		m = (a - 1) * (b - 1);
	}
	
	/**
	 * Generate a pair of keys
	 * @return a KeyPair
	 */
	public KeyPair generateKeys() {
		int pubKey = coprime(m);
		int priKey = modInverse(pubKey, m);
		System.out.println("First Prime = " + a + ", Second Prime = " + b + ", c = " + c +
				", m = " + m + ", e = " + pubKey + ", d = " + priKey + ", Public Key = (" + pubKey + 
				", " + c + "), Private Key = (" + priKey + ", " + c + ")");
		return new KeyPair(pubKey, priKey, c);
	}
	
	/**
	 * Find coprime number to x
	 * @param x original number
	 * @return coprime number
	 */
	public static int coprime(int x) {
		while(true) {
			Random ran = new Random();
			int y = ran.nextInt(x - 2) + 2; 
			if(gcd(y, x) == 1) return y; 
		}
	}
	
	/**
	 * Find mod inverse
	 * @param base the base
	 * @param m the m value
	 * @return mod inverse value
	 */
	public static int modInverse(int base, int m) {
		int ex = totient(m);
		return modulo(base, ex - 1, m);
	}
		
	/**
	 * Find gcd of two integers
	 * @param x first integer
	 * @param y second integer
	 * @return gcd of the two integers
	 */
	public static int gcd(int x, int y) {
		int a = x > y? x : y;
		int b = x <= y? x : y;
		int comDiv = 1;
		for(int i = 1; i <= b; i++) {
			if(b % i == 0 && a % i == 0) {
				comDiv = i;
			}
		}
		return comDiv;
	}
	
	/**
	 * Find modulo of pow(x,y) to z
	 * @param x base
	 * @param y exponent
	 * @param z mod base
	 * @return modulo value
	 */
	public static int modulo(int x, int y, int z) {
		int result = 1;
		for(int i = 0; i < y; i++) {
			result = result * x % z;
		}
		return result;
	}
	
	/**
	 * Find the totient of x
	 * @param x input value
	 * @return totient
	 */
	public static int totient(int x) {
		int phi = 1;
        for(int i = 2 ; i < x; ++i) {
            if(gcd(i, x) == 1) ++phi;
        }
        return phi;
	}
	
	/**
	 * Find nth prime number
	 * @param n index
	 * @return nth prime number
	 */
	public static int findPrime(int n) {
		if(n <= 0) throw new RuntimeException("Invalid prime index!");
		int i = 0;
		int number = 1;
		while(i < n) {
			number++;
			if(isPrime(number)) {
				i++;
			}
		}
		return number;
	}
	
	/**
	 * Decide whether an integer is prime
	 * @param n the number to be tested
	 * @return true if it is a prime number
	 */
	public static boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
        int limit = (int)(Math.sqrt(n)+ 0.5);
        for (int i = 3; i <= limit; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
	
}
