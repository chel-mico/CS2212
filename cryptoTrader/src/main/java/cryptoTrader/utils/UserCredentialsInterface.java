package cryptoTrader.utils;

/**
 * 
 * @author Group 34
 * An interface used to describe the interaction between the program and the JSON file
 *
 */
public interface UserCredentialsInterface {

	/**
	 * Encrypts the user's inputted password so it can match the encrypted passwords in the
	 * JSON file. Uses a modified version of the sdbm hash
	 * @param password The user's input in the password field
	 * @return The encrypted version of the password
	 */
	public String encrypt(String password);
	
	/**
	 * Attempts to find the provided username in the JSON file. If record exists, returns the encrypted password.
	 * @param username
	 * @return The encrypted password
	 */
	public String fetchUser(String username);
}
