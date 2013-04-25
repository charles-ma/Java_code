/**
 * 
 */
package rsa;

/**
 * Key
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class Key {
	
	private int exp, mod;
	
	/**
	 * Constructor for Key
	 * @param exp exponent para
	 * @param mod base para
	 */
	public Key(int exp, int mod) {
		this.exp = exp;
		this.mod = mod;
	}
	
	/**
	 * Get exponent part of the key
	 * @return exponent part
	 */
	public int getExp() {
		return exp;
	}
	
	/**
	 * Get base part of the key
	 * @return base part
	 */
	public int getMod() {
		return mod;
	}
}
