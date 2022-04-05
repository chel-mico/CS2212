package cryptoTrader.gui;

/**
 * 
 * @author Group 34
 * A factory that generates pop-up windows
 *
 */
public class AlertFactory {

	/**
	 * Creates a pop up window for the user to see and interact with
	 * @param popType The type of alert window to be shown
	 * @return An alarm window
	 */
	
	// Following the singleton design pattern, only one instance of the login menu is needed
	private static AlertFactory instance = new AlertFactory();
	
	public Alert getAlert(String popType) {
		
		// If given null type, don't instantiate any type of pop-up
		if (popType == null) {
			return null;
		}
		
		// Returns an error pop-up during the login menu
		if (popType.equals("loginError")) {
			return new LoginError();
		}
		
		// Return null by default
		return null;
		
	}
	
	/**
	 * An accessor method that returns the solo instance of the Alert Factory
	 * @return The Alert Factory
	 */
	public static AlertFactory getInstance() {
		return instance;
	}
}
