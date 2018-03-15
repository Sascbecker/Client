package logik;

import java.util.ArrayList;

/**
 * implementation of the abstractChat for direct messaging a contact.
 * @author alnenno
 */
public class ContactChat extends AbstractChat {

	@Override
	public int getGroupID() {
		return 0;	//since we're not a group, we return groupID 0.
	}
	
	/**
	 * full constructor for a DM chat
	 */
	public ContactChat(String chatName) {
		this.chatName = chatName;
		this.unreadMessages = 0;
		this.history = new ArrayList<Message>();
	}

}
