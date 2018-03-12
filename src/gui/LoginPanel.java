package gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	
	private JTextField usernameField;
	private JTextField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private LayoutManager layout;
	private JButton loginButton;
	
	private static final String USERNAME_STRING = "Nutzername:";
	private static final String PASSWORD_STRING = "Passwort:";
	private static final String BUTTON_STRING = "Login";

	
	public LoginPanel() {
		usernameField = new JTextField();
		passwordField = new JTextField();
		usernameLabel = new JLabel(USERNAME_STRING);
		passwordLabel = new JLabel(PASSWORD_STRING);
		loginButton = new JButton(BUTTON_STRING);
		layout = new GridLayout(3, 2);
		this.setLayout(layout);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
		
		
	}
}
