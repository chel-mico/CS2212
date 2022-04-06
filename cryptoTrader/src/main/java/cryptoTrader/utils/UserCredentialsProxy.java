package cryptoTrader.utils;

/**
 * A proxy class used for safety and security to access UserCredentials
 * @author Group 34
 *
 */
public class UserCredentialsProxy {
	
	// Following the singleton design pattern, only one instance of the UserCredentialsProxy is needed
	private static UserCredentialsProxy instance = new UserCredentialsProxy();
	
	// The actual UserCredentials object
	private UserCredentials userCredentials;
	
	/**
	 * Private constructor because only one instance
	 */
	private UserCredentialsProxy() {
		userCredentials = UserCredentials.getInstance();
	}
	
	/**
	 * Encrypts the user's inputted password so it can match the encrypted passwords in the
	 * JSON file. Uses a modified version of the sdbm hash
	 * @param password The user's input in the password field
	 * @return The encrypted version of the password
	 */
	public String encrypt(String password) {
		return userCredentials.encrypt(password);
	}
	
	/**
	 * Attempts to find the provided username in the JSON file. If record exists, returns the encrypted password.
	 * @param username
	 * @return The encrypted password
	 */
	public String fetchUser(String username) {
		return userCredentials.fetchUser(username);
	}
	
	/**
	 * An accessor method that returns the solo instance of the user credentials proxy
	 * @return The user credentials proxy
	 */
	public static UserCredentialsProxy getInstance() {
		return instance;
	}
}
