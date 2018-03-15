package logik;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import gui.ChatFrame;
import gui.LoginFrame;
import rest.ServerConnector;

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
		try {
			ServerConnector.main(null);
		} catch (Throwable t) {}
		myProgram.begin();
		//myProgram.offlineGuiTest();
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
		System.out.println("window created");

		((ChatFrame) mainwindow).AddChat(new ContactChat("hallo"));
		((ChatFrame) mainwindow).AddChat(new ContactChat("welt"));
		System.out.println("we added two chats");
		System.out.println(((ChatFrame) mainwindow).getChatList());
		testMSG("hallo", "wie geht's denn so", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "kjdsafljdsaöljf", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "hallohasdkjsaj", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "AAAAAAAAAAAA", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "bummbumm", 0, ((ChatFrame) mainwindow));
		testMSG("welt", "aaaa", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "texttexttext", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "uiuiuiuiuiuiuiuiu", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "pups", 0, ((ChatFrame) mainwindow));
		testMSG("hallo", "bumm", 0, ((ChatFrame) mainwindow));
		testMSG("welt", "ich will nicht mehr", 0, ((ChatFrame) mainwindow));
		testMSG("welt", "alles ist kaputt", 0, ((ChatFrame) mainwindow));
		
		List<String> userlist = new ArrayList<String>();
		userlist.add("Hallo");
		userlist.add("Welt");
		userlist.add("Testnutzer");
		userlist.add("Mond");
		userlist.add("DAU");
		userlist.add("Ein Schwein");

		((ChatFrame)mainwindow).AddChat(new GroupChat(777, "arschloch gruppe", "Testnutzer", userlist));
		
		testMSG("hallo", "alfdsajkt kaputt", 777, ((ChatFrame) mainwindow));
		testMSG("welt", "fölkdsjaöflkst kaputt", 777, ((ChatFrame) mainwindow));
		testMSG("Ein Schwein", "vomit coffin", 777, ((ChatFrame) mainwindow));
		testMSG("Mond", "alles", 777, ((ChatFrame) mainwindow));
		testMSG("DAU", "kaputt", 777, ((ChatFrame) mainwindow));
		testMSG("Testnutzer", "ALLES IST KAPUTT", 777, ((ChatFrame) mainwindow));

		
	}
	private void testMSG(String sender, String msg, int groupID, ChatFrame window) {
		Random rng = new Random();
		window.receiveMessage(new Message(
				sender, 
				groupID, 
				"Testnutzer", 
				msg, 
				System.currentTimeMillis()-rng.nextInt(100_000)
				));
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
