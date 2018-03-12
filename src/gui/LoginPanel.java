package gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private LayoutManager layout;
	private JButton loginButton;
	private JLabel errorLabel;
	private boolean loggedIn;
	private String username;
	private String password;
	
	private static final String USERNAME_STRING = "Nutzername:";
	private static final String PASSWORD_STRING = "Passwort:";
	private static final String BUTTON_STRING = "Login";
	
	protected static final String ERR_NO_USERNAME = "bitte Nutzername eingeben";
	protected static final String ERR_NO_PASSWORD = "bitte Passwort eingeben";
	protected static final String ERR_LOGIN_FAILURE = "Fehler beim einloggen. Bitte Netzwerk überprüfen";
	private static final String MSG_LOGIN_SUCCESS = "Erfolgreich eingeloggt!";


	
	public LoginPanel() {
		loggedIn=false;
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		usernameLabel = new JLabel(USERNAME_STRING);
		passwordLabel = new JLabel(PASSWORD_STRING);
		loginButton = new JButton(BUTTON_STRING);
		errorLabel = new JLabel();
		layout = new GridLayout(3, 2);
		this.setLayout(layout);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
		this.add(errorLabel);
		setButtonListener();
		
		
	}
	
	private void setButtonListener() {
		loginButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}//do nothing
			@Override
			public void mousePressed(MouseEvent e) {}//do nothing
			@Override
			public void mouseExited(MouseEvent e) {}//do nothing
			@Override
			public void mouseEntered(MouseEvent e) {}//do nothing
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("hallo");
				if(usernameEmpty()) {
					errorLabel.setText(ERR_NO_USERNAME);
					return;
				}
				if(passwordEmpty()) {
					errorLabel.setText(ERR_NO_PASSWORD);
					return;
				}
				try {
					login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
					//wenn erfolgreich tritt folgendes ein:
					errorLabel.setText(MSG_LOGIN_SUCCESS);
					loginSuccess();
					
				}catch(Throwable t) {
					errorLabel.setText(ERR_LOGIN_FAILURE);
				}

			}
		});
	}
	
	protected void loginSuccess() {
		username = usernameField.getText();
		password = String.valueOf(passwordField.getPassword());
		loggedIn = true;
	}
	public boolean getLoggedIn() {
		return loggedIn;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	private void login(String text, String valueOf) {
		//TODO: login beim server
		//wenn fehler beim login auftritt (netzwerkfehler oder sonst irgendwas)
		//throw new RuntimeException("irgendwas ist schief gelaufen");
		//exception werfen. keine unterscheidung nötig.
	}

	private boolean passwordEmpty() {
		return(String.valueOf(passwordField.getPassword()).equals(""));
	}

	private boolean usernameEmpty() {
		return(usernameField.getText()==null || usernameField.getText().equals(""));
	}
}
