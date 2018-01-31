package clientConnector;

import java.util.LinkedList;

public class Singleton {
	  
	private static Singleton instance;
	private LinkedList<String> messages;
	private Singleton () {
		messages = new LinkedList<String>();
	}
	
	public static synchronized void setInstanceAndMessage (String message) {
		if (Singleton.instance == null) {
			Singleton.instance = new Singleton ();
			Singleton.instance.messages.add(new String (message));
		}
	}
	
	public static String getMessage () {
		return Singleton.instance.messages.removeFirst();
	}
}
