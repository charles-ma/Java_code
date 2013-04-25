/**
 * 
 */
package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * To encrypt and decrypt string
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class Cipher {

	/**
	 * Main function for Cipher
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please enter the public key (e, c): first e, then c");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		String[] nums = input.split(" ");
		int e = Integer.parseInt(nums[0]);
		int c = Integer.parseInt(nums[1]);
		System.out.println("Please enter a sentence to encrypt");
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		String a = new Cipher().encrypt(input, new Key(e, c));
		System.out.println(a);
		System.out.println("Please enter the private key (d, c): first d, then c");
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		nums = input.split(" ");
		int d = Integer.parseInt(nums[0]);
		int c1 = Integer.parseInt(nums[1]);
		System.out.println("Enter all the codes(seperated by a space)");
		try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}
		a = new Cipher().decrypt(input, new Key(d, c1));
		System.out.println(a);
	}
	
	/**
	 * Encrypt a message 
	 * @param oriMessage the original message
	 * @param pubKey public key
	 * @return encrypted message
	 */
	public synchronized String encrypt(String oriMessage, Key pubKey) {
		char c;
		StringBuffer encryptedMessage = new StringBuffer();
		for(int i = 0; i < oriMessage.length(); i++) {
			c = oriMessage.charAt(i);			
			int ascii = (int)c;
			int encCode = RSAKeyGenerator.modulo(ascii, pubKey.getExp(), pubKey.getMod());
			encryptedMessage.append(encCode + " ");
		}		
		return new String(encryptedMessage);
	}
	
	/**
	 * Decrypt the message
	 * @param encryptMessage encripted message
	 * @param priKey private key
	 * @return decrypted message
	 */
	public synchronized String decrypt(String encryptMessage, Key priKey) {
		String words[] = encryptMessage.split(" ");
		StringBuffer decryptMessage = new StringBuffer();
		for(int i = 0; i < words.length; i++) {
			if(words[i].charAt(0) < 48 || words[i].charAt(0) > 57) continue;
			int encCode = Integer.parseInt(words[i]);
			int ascii = RSAKeyGenerator.modulo(encCode, priKey.getExp(), priKey.getMod());
			char c = (char)ascii;
			decryptMessage.append(c);
		}
		return new String(decryptMessage);
	}	
	
}
