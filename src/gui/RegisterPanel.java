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

public class RegisterPanel extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmationField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmationLabel;
	private LayoutManager layout;
	private JButton loginButton;
	private JLabel errorLabel;
	
	private static final String USERNAME_STRING = "Nutzername:";
	private static final String PASSWORD_STRING = "Passwort:";
	private static final String PASSWORD_CONFIRMATION_STRING = "Passwort bestätigen:";
	private static final String BUTTON_STRING = "Registrieren";
	private static final String ERR_PASSWORDS_DIFFERENT = "Die Passwörter sind nicht gleich";
	protected static final String ERR_NO_USERNAME = "bitte Nutzername eingeben";
	protected static final String ERR_NO_PASSWORD = "bitte Passwort eingeben";
	protected static final String ERR_REGISTRATION_FAILURE = "Fehler beim registrieren. Bitte Netzwerk überprüfen";
	private static final String MSG_REGISTRATION_SUCCESS = "Erfolgreich Registriert!";

	
	public RegisterPanel() {
		usernameLabel = new JLabel(USERNAME_STRING);
		usernameField = new JTextField();
		passwordLabel = new JLabel(PASSWORD_STRING);
		passwordField = new JPasswordField();
		passwordConfirmationLabel = new JLabel(PASSWORD_CONFIRMATION_STRING);
		passwordConfirmationField = new JPasswordField();
		errorLabel = new JLabel();
		
		loginButton = new JButton(BUTTON_STRING);
		layout = new GridLayout(4, 2);
		this.setLayout(layout);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(passwordConfirmationLabel);
		this.add(passwordConfirmationField);
		setButtonListener();
		this.add(loginButton);
		this.add(errorLabel);
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
				if(!passwordsMatch()) {
					errorLabel.setText(ERR_PASSWORDS_DIFFERENT);
					return;
				}
				try {
					register(usernameField.getText(), String.valueOf(passwordField.getPassword()));
					//wenn erfolgreich tritt folgendes ein:
					errorLabel.setText(MSG_REGISTRATION_SUCCESS);
					
				}catch(Throwable t) {
					errorLabel.setText(ERR_REGISTRATION_FAILURE);
				}

			}
		});
	}
	
	protected void register(String text, String valueOf) {
		//TODO: registrieren beim server
		//wenn fehler bei der registration auftritt (netzwerkfehler oder sonst irgendwas)
		throw new RuntimeException("irgendwas ist schief gelaufen");
		//exception werfen. keine unterscheidung nötig.
	}

	private boolean passwordEmpty() {
		return(String.valueOf(passwordField.getPassword()).equals(""));
	}

	private boolean usernameEmpty() {
		return(usernameField.getText()==null || usernameField.getText().equals(""));
	}

	/**
	 * checks whether the string in the password field and the password confirmation field match
	 * @return
	 */
	private boolean passwordsMatch() {
		System.out.println("pw:"+String.valueOf(passwordField.getPassword()));
		System.out.println("pw:"+String.valueOf(passwordConfirmationField.getPassword()));

		return(String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordConfirmationField.getPassword())));
	}

}
