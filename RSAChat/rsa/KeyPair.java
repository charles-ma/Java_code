/**
 * 
 */
package rsa;

/**
 * KeyPair
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class KeyPair {
	
	private Key pubKey;
	private Key priKey;
	
	/**
	 * Constructor for KeyPair
	 * @param pubKey public key
	 * @param priKey private key
	 * @param base base value
	 */
	public KeyPair(int pubKey, int priKey, int base) {
		this.pubKey = new Key(pubKey, base);
		this.priKey = new Key(priKey, base);
	}
	
	/**
	 * Get public key
	 * @return public key
	 */
	public Key getPublicKey() {
		return pubKey;
	}
	
	/**
	 * Get private key
	 * @return private key
	 */
	public Key getPrivateKey() {
		return priKey;
	}
}
