package logik;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of AbstractChat for group Chats
 * @author alnenno
 *
 */
public class GroupChat extends AbstractChat {
	private int groupID;
	private String admin;
	private List<String> users;
	
	/**
	 * full constructor for a group chat
	 */
	public GroupChat(int groupID, String chatName, String admin, List<String> users) {
		this.groupID = groupID;
		this.admin = admin;
		this.users = users;
		this.chatName = chatName;
		this.unreadMessages = 0;
		this.history = new ArrayList<Message>();
	}
	
	@Override
	public int getGroupID() {
		return groupID;
	}
	public List<String> getUsers(){
		return users;
	}
	public String getAdmin() {
		return admin;
	}

}
