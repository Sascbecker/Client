package gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel {
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField passwordConfirmationField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmationLabel;
	private LayoutManager layout;
	private JButton loginButton;
	
	private static final String USERNAME_STRING = "Nutzername:";
	private static final String PASSWORD_STRING = "Passwort:";
	private static final String PASSWORD_CONFIRMATION_STRING = "Passwort bestätigen:";
	private static final String BUTTON_STRING = "Login";

	
	public RegisterPanel() {
		usernameLabel = new JLabel(USERNAME_STRING);
		usernameField = new JTextField();
		passwordLabel = new JLabel(PASSWORD_STRING);
		passwordField = new JTextField();
		passwordConfirmationLabel = new JLabel(PASSWORD_CONFIRMATION_STRING);
		passwordConfirmationField = new JTextField();
		
		loginButton = new JButton(BUTTON_STRING);
		layout = new GridLayout(4, 2);
		this.setLayout(layout);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(passwordConfirmationLabel);
		this.add(passwordConfirmationField);
		this.add(loginButton);
		
		
	}
}
