package cryptoTrader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import cryptoTrader.gui.Login;

/**
 * @author Group 34
 * The component that helps the Login Menu read the encrypted JSON file that
 * contains the Login information.
 *
 */
public class UserCredentials implements UserCredentialsInterface {

	// Following the singleton design pattern, only one instance of the UserCredentials is needed
	private static UserCredentials instance = new UserCredentials();
	
	// An instance of ClassLoader and Scanner that is needed to read the JSON and hash
	ClassLoader classLoader;
	Scanner input;
	
	/**
	 * The class constructor. Private because only one instance.
	 */
	private UserCredentials() {
		classLoader = getClass().getClassLoader();
	}
	
	/**
	 * Encrypts the user's inputted password so it can match the encrypted passwords in the
	 * JSON file. Uses a modified version of the sdbm hash
	 * @param password The user's input in the password field
	 * @return The encrypted version of the password
	 */
	public String encrypt(String password) {
		// Reads the hash provided in hash.txt
		File tempFile = new File(classLoader.getResource("hash.txt").getFile());
		try {
			input = new Scanner(new FileReader(tempFile));
			String wordHash = input.nextLine();
			
			// Converts the word hash into a numeric value by adding their ASCII values
			char[] letters = wordHash.toCharArray();
			int hashValue = 0;
			for (char letter : letters) {
				hashValue += (int) letter;
			}
			
			// sdbm hash
			for (int x = 0; x < password.length(); x++) {
				hashValue = password.charAt(x) + ((hashValue << 5) - hashValue);
			}
			
			// more hashing so the hash is not solely numbers
			String hashString = String.valueOf(hashValue);
			String encryptedPass = "";
			for (int x = hashString.length(); x != 1; x-=1) {
				encryptedPass += (Character.toString((char) (hashString.charAt(x-1) * 4)) + Character.toString((char) (hashString.charAt(x-2) * 2)));
			}
			
			// Returns the encrypted password
			return encryptedPass;
		}
		// If the hash isn't found, output the error in console and return an empty string
		catch (FileNotFoundException e){
			System.out.println(e);
		}
		return "";
	}
	
	/**
	 * Attempts to find the provided username in the JSON file. If record exists, returns the encrypted password.
	 * @param username
	 * @return The encrypted password
	 */
	public String fetchUser(String username){
		try {
			// Loads up the encrypted JSON file
			File tempFile = new File(classLoader.getResource("credentials.json").getFile());
			input = new Scanner(new FileReader(tempFile));
			String parsedUser = "";
			String parsedPass = "";
			
			// Reads through the JSON line by line
			while (input.hasNextLine()) {
				String tempLine = input.nextLine();
				
				// If a line contains "username" compare the usernames
				if (tempLine.contains("username")) {
					
					// Removes the JSON format for a clean comparison with user inputted username
					tempLine = tempLine.replace(" ", "").replace("\"", "").replace(",", "");
					parsedUser = tempLine.substring(9);
					
					tempLine = input.nextLine();
					tempLine = tempLine.replace(" ", "").replace("\"", "");
					parsedPass = tempLine.substring(9);
					
					// If username is found, returns the encrypted password which the line after the username
					if (parsedUser.toLowerCase().equals(username)) {
						return parsedPass;
					}
					else {
						continue;
					}
				}
			}
			
			// Return an empty string if username doesn't exist
			return("");
		}
		
		// If file isn't found output error in console and return an empty string
		catch (FileNotFoundException e){
			System.out.println(e);
		}
		return "";
	}
	
	/**
	 * An accessor method that returns the solo instance of the user credentials
	 * @return The user credentials
	 */
	public static UserCredentials getInstance() {
		return instance;
	}
}
