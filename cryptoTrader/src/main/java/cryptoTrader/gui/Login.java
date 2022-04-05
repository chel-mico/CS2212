package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cryptoTrader.utils.UserCredentials;
import cryptoTrader.gui.MainUI;

/**
 * @author Group 34
 * The Login menu. Users interact with this class by inputting their login information.
 * If given correct credentials, users are given access to the main UI.
 */
public class Login extends JFrame implements ActionListener {

	// Following the singleton design pattern, only one instance of the login menu is needed
	private static Login instance = new Login();
	
	// Top part of the menu that gives the user instructions
	private JPanel loginPanel;
	private JLabel loginLabel;
	
	// The Login menu uses the Grid Bag layout so it looks like a traditional login screen
	private GridBagConstraints gbc;
	private JPanel infoPanel;
	
	// Middle part of the menu where the user inputs their login information
	private JLabel userLabel;
	private JTextField userField;
	private JLabel passLabel;
	private JPasswordField passField;
	
	// Bottom part of the menu where the user can confirm their login with a click of a button
	private JPanel buttonPanel;
	private JButton loginButton;
	
	// Alert Factory to create a pop-up if an error is shown
	private static AlertFactory alertFactory;
	/**
	 * The class constructor. Private because only one instance.
	 */
	private Login() {
		
		// Set the window's title and size
		super("Login Menu");
		super.setSize(400, 200);
		super.setResizable(false);
		
		// Continues to instantiate those previously declared
		
		loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loginLabel = new JLabel("Please fill in your crendentials.");
		
		gbc = new GridBagConstraints();
		infoPanel = new JPanel(new GridBagLayout());
		
		userLabel = new JLabel("User:");
		userField = new JTextField(15);
		passLabel = new JLabel("Password:");
		passField = new JPasswordField(15);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginButton = new JButton("Login");
		
		alertFactory = AlertFactory.getInstance();
		
		// Set the layout of the menu as I described during instantiation
		loginPanel.add(loginLabel);
		gbc.gridx = 0;
		gbc.gridy = 0;
		infoPanel.add(userLabel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		infoPanel.add(userField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		infoPanel.add(passLabel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		infoPanel.add(passField, gbc);
		
		// Add an eventListener to the button
		loginButton.addActionListener(this);
		buttonPanel.add(loginButton);
		
		// Add the panels onto the window
		super.add(loginPanel, BorderLayout.PAGE_START);
		super.add(infoPanel, BorderLayout.CENTER);
		super.add(buttonPanel, BorderLayout.PAGE_END);
	
		// Make the window visible
		super.setVisible(true);
	}
	
	/**
	 * The actionListener for the login button. When clicked, the program checks if the user
	 * gave a proper set of username and password.
	 * @param event An event that took place within the menu. There is only one login button, so it's only
	 * 		  the login button's listener.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(loginButton)) {
			verifyUser(userField.getText(), String.valueOf(passField.getPassword()));
		}		
	}
	
	/**
	 * A helper method for the login button. Encrypts the password and checks to see if there is a match in
	 * the encrypted JSON file.
	 * @param username The user's input in the username field
	 * @param password The user's input in the password field
	 */
	public void verifyUser(String username, String password) {
		
		// Encrypts the given password and checks if there is a match
		UserCredentials userCredentials = UserCredentials.getInstance();
		String checkedPassword = userCredentials.encrypt(password);
		String hashedPassword = userCredentials.fetchUser(username);
		
		// Launch the MainUI if a set of username and password matches
		if (hashedPassword.equals(checkedPassword) && !hashedPassword.equals("")) {
			JFrame frame = MainUI.getInstance();
			frame.setSize(900, 600);
			frame.pack();
			frame.setVisible(true);
			
			// Closes the LoginUI
			this.setVisible(false);
			this.dispose();
		}
		// Inform the user they didn't enter valid login info if no match is found and closes the UI
		else {
			new AlertFactory().getAlert("loginError");
			this.setVisible(false);
			this.dispose();
		}
	}
	
	/**
	 * An accessor method that returns the solo instance of the login menu
	 * @return The login menu
	 */
	public static Login getInstance() {
		return instance;
	}
	
	/**
	 * The main function only used for debugging. Delete later.
	 * @param args
	 */
	public static void main(String[] args) {
		Login testLogin = Login.getInstance();
	}

}
