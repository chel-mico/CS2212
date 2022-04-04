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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame implements ActionListener {

	JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel loginLabel = new JLabel("Please fill in your crendentials.");
	
	
	GridBagConstraints gbc = new GridBagConstraints();
	JPanel infoPanel = new JPanel(new GridBagLayout());
	JLabel userLabel = new JLabel("User:");
	JTextField userField = new JTextField(15);
	JLabel passLabel = new JLabel("Password:");
	JTextField passField = new JTextField(15);
	
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JButton loginButton = new JButton("Login");
	
	public Login() {
		super("Login Menu");
		super.setSize(400, 200);
		super.setResizable(false);
		
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
		buttonPanel.add(loginButton);
		super.add(loginPanel, BorderLayout.PAGE_START);
		super.add(infoPanel, BorderLayout.CENTER);
		super.add(buttonPanel, BorderLayout.PAGE_END);
	
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
