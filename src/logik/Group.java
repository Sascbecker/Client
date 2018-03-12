package logik;

import java.util.List;

/**
 * data class for groups
 * @author alnenno
 *
 */
public class Group {
	private String admin;
	private List<String> userList;
	private List<Message> history;
	
	public List<String> getUserList(){
		return userList;
	}
	public List<Message> getHistory(){
		return history;
	}
	public void appendNewMessagesToHistory(List<Message> newMessages) {
		history.addAll(newMessages);
	}
	public String getAdmin() {
		return admin;
	}




}
