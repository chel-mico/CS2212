package cryptoTrader.gui;

/**
 *
 * An interface used to describe a window that alerts the user of something that happened in the program 
 *  @author Group 34
 */
public interface Alert {
	
	/**
	 * Sets the title and message of the window
	 * @param title The title of the pop-up window
	 * @param message The message shown on the pop-up window
	 */
	public void setMessage(String title, String message);
}
