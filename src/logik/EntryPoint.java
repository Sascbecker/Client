package logik;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gui.ChatFrame;
import gui.LoginFrame;

/**
 * entry point for the whole client program
 * @author alnenno
 *
 */
public class EntryPoint {
	private String name;
	private String password;
	

	public static void main(String[] args) {
		EntryPoint myProgram = new EntryPoint();
		
		//myProgram.begin();
		myProgram.offlineGuiTest();
	}
	
	/**
	 * dynamic entry point to the program
	 */
	public void begin() {
		JFrame mainwindow = new LoginFrame();
		boolean loggedIn = false;
		while(!loggedIn) {
			this.sleep(250);
			loggedIn = ((LoginFrame)mainwindow).getLoggedIn();
		}
		this.name = ((LoginFrame)mainwindow).getUserName();
		this.password = ((LoginFrame)mainwindow).getPassword();
		mainwindow = new ChatFrame(name);
		
	}
	
	/**
	 * dynamic entry point to the offline test for the GUI
	 * skips the login screen, which has been thoroughly tested at this point
	 * and immediately constructs an example chat window, which is difficult to test offline
	 * thus test data is generated in this method.
	 */
	public void offlineGuiTest() {
		JFrame mainwindow = new ChatFrame("Testnutzer");		
	}
	
	/**
	 * eliminating unnecessary boilerplate code around sleep
	 */
	private void sleep(long ms) {
		try {
			Thread.sleep(ms);//sleep 0,25 seconds
		}catch(Throwable t) {
			//pups
		}
	}

}
