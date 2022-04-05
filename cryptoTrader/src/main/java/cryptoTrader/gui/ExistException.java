package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Group 34
 * A pop up that alerts the user they entered the wrong login information
 *
 */
public class ExistException extends JFrame implements Alert, ActionListener {
	
	// Top part of the menu that tells the user why they received a pop-up
	private JLabel alertMessage;
	private JPanel alertPanel;
	
	// Bottom part of the menu where the user can close the pop-up
	private JButton confirmButton;
	private JPanel buttonPanel;
	
	/**
	 * The class constructor
	 * @param title The title of the pop-up window
	 * @param message The message shown on the pop-up window
	 */
	public ExistException() {
		
		// Sets the window size
		super.setSize(400, 100);
		super.setResizable(false);
		
		// Continues to instantiate those previously declared
		confirmButton = new JButton("Ok");
		alertPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Sets the title and message of the window
		setMessage("Broker exists!", "Please choose another name.");
		super.add(alertPanel, BorderLayout.PAGE_START);
		
		// Add an eventListener to the button and to the window
		confirmButton.addActionListener(this);
		buttonPanel.add(confirmButton);
		super.add(buttonPanel, BorderLayout.PAGE_END);
		
		// Makes the window visible
		super.setVisible(true);
	}
	
	@Override
	/**
	 * Sets the title and message of the window
	 * @param title The title of the pop-up window
	 * @param message The message shown on the pop-up window
	 */
	public void setMessage(String title, String message) {
		this.setTitle(title);
		alertMessage = new JLabel(message);
		alertPanel.add(alertMessage);
	}
	
	@Override
	/**
	 * The actionListener for the confirm button. When clicked, the window closes
	 * @param event An event that took place within the menu. There is only one button, so it's only
	 * 		  the confirm button's listener.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(confirmButton)) {
			// Closes the pop up window
			this.setVisible(false);
			this.dispose();
		}		
	}
	
}