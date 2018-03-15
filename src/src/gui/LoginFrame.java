package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * window that offers the user the option to login or register
 * contains a LoginPanel and a RegisterPanel
 * 
 * upon successful registration, a ChatFrame is opened instead.
 * 
 * @author alnenno
 *
 */
public class LoginFrame extends JFrame {
	//do the l and r in the variable name stand for login and register or left and right?
	//nobody will ever know!
	private LoginPanel lpanel;
	private RegisterPanel rpanel;
	private JSplitPane splitPane;
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final String TITLE_STRING = "Einloggen oder Registrieren";
	
	public LoginFrame() {
		lpanel = new LoginPanel();
		rpanel = new RegisterPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lpanel, rpanel);
		this.setTitle(TITLE_STRING);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		splitPane.setDividerLocation(DEFAULT_WIDTH/2);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(DEFAULT_WIDTH/3, DEFAULT_HEIGHT/3);
		lpanel.setMinimumSize(minimumSize);
		rpanel.setMinimumSize(minimumSize);
		this.add(splitPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public boolean getLoggedIn() {
		if(lpanel!=null) {
			return lpanel.getLoggedIn();
		}else {
			return false;
		}
			
	}
	public String getUserName() {
		if(lpanel!=null) {
			return lpanel.getName();
		}else {
			return null;
		}
	}
	public String getPassword() {
		if(lpanel!=null) {
			return lpanel.getPassword();
		}else {
			return null;
		}
	}

}
