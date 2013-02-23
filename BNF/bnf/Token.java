/**
 * 
 */
package bnf;

/**
 * This class stores the information of tokens
 * @author machao
 * @version 1.0
 */
public class Token {
	
	enum TokenType {NONTERMINAL, TERMINAL, METASYMBOL, SEQUENCE, OR, OPTION, ANYNUM};
	private TokenType type = TokenType.NONTERMINAL;
	private String value = "";
	
	/**
	 * Constructor of the Token class
	 * @param type the type of the token(NONTERMINAL, TERMINAL, METASYMBOL, SEQUENCE, OR, OPTION, ANYNUM)
	 * @param value the value of the token
	 */
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Gets the type of this token
	 * @return the type of the token
	 */
	public TokenType getType() {
		return this.type;
	}
	
	/**
	 * Gets the value of the token
	 * @return the value of the token
	 */
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return this.value;
	}
}
