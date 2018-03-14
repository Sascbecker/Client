package logik;

import java.util.List;
/**
 * the logic layer representation of a chat.
 * both groupChats and directChats extend this class.
 * @author alnenno
 *
 */
public abstract class AbstractChat {
	protected List<Message> history;
	protected int unreadMessages;
	protected String chatName;	
	/**
	 * appends any new messages to the message history of the chat
	 * @param messages the list of new messages to append
	 */
	public void appendNewMessagesToHistory(List<Message> newMessages) {
		unreadMessages += newMessages.size();
		history.addAll(newMessages);
	}
	/**
	 * appends a new message to the message history of the chat
	 * @param newMessage the new message to append
	 */
	public void appendNewMessageToHistory(Message newMessage) {
		unreadMessages++;
		history.add(newMessage);
	}
	/**
	 * returns the message history
	 */
	public List<Message> getHistory(){
		return history;
	}
	/**
	 * returns the name of the chat; either the name of the group or the name of the contact
	 */
	public String getChatName() {
		return chatName;
	}
	/**
	 * returns the amount of unread messages
	 */
	public int getUnreadMessages() {
		return unreadMessages;
	}
	/**
	 * sets the amount of unreadMessages to 0
	 */
	public void readMessages() {
		unreadMessages = 0;
	}
	/**
	 * if the chat is a groupchat, returns the groupID
	 * else returns 0
	 */
	public abstract int getGroupID();
	
}
