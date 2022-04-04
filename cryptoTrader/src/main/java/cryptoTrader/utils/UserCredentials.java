package cryptoTrader.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserCredentials {

	public UserCredentials() {
	}
	
	public Map<String,String> fetchUser(String username){
		try {
			FileReader userDatabase = new FileReader("C:\\Users\\Victor\\Documents\\GitHub\\CS2212\\cryptoTrader\\src\\main\\resources\\credentials.json");
			Scanner input = new Scanner(userDatabase);
			String parsedUser = "";
			String parsedPass = "";
			HashMap<String, String> tempMap = new HashMap<String,String>();
			while (input.hasNextLine()) {
				String tempLine = input.nextLine();
				if (tempLine.contains("username")) {
					tempLine = tempLine.replace(" ", "").replace("\"", "").replace(",", "");
					parsedUser = tempLine.substring(9);
					
					tempLine = input.nextLine();
					tempLine = tempLine.replace(" ", "").replace("\"", "");
					parsedPass = tempLine.substring(9);
					
					if (parsedUser.toLowerCase().equals(username)) {
						tempMap.put(username,parsedPass);
						return tempMap;
					}
					else {
						continue;
					}
				}
			}
			tempMap.put("", "");
			return tempMap;
		}
		catch (FileNotFoundException e){
			System.out.println(e);
		}
		return null;
	}
	
	public static void main(String args[]) {
		UserCredentials test = new UserCredentials();
		System.out.println(test.fetchUser("victor", "test").get("victor"));
	}
}
