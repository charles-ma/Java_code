/**
 * 
 */
package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Key Cracker
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class KeyCracker {

	/**
	 * Main function for KeyCracker
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please enter the public key value");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		int e = Integer.parseInt(input);
		System.out.println("Please enter the c value");
		inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		int c = Integer.parseInt(input);
		crack(e, c);
	}

	/**
	 * Crack a key
	 * @param e public key value
	 * @param c base value
	 */
	public static void crack(int e, int c) {
		
		int phi = RSAKeyGenerator.totient(c); 
		int d = 0;
				
		for(int i = 1; i < phi; i++) {
			if((i * phi + 1) % e == 0) {
				d = (i * phi + 1) / e;
				break;
			}
		}
		System.out.println("D was found to be " + d);
		Cipher cipher = new Cipher();
		Key priKey = new Key(d, c);
		String input = "";
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		while(!input.equals("quit")) {
			System.out.println("Please enter a code to decrypt(quit to quit)");
			try {
				input = inFromUser.readLine();
			} catch (IOException ex) {
				System.out.println("Read in error!");
			}
			System.out.println(cipher.decrypt(input, priKey));
		}
	}
}
