package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.List;

import logik.AbstractChat;
import logik.Message;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class ChatFrame extends JFrame {
	
	private static final String TITLE_STRING = null;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private JPanel chatListPanel;
	private LayoutManager chatListPanelLayout;
	private JPanel chatPanel;
	private JSplitPane splitPane;
	private JButton buttonNewContact;
	private JButton buttonNewGroup;

	
	private String username;
	private List<AbstractChat> chatList;
	
	public ChatFrame(String username) {
		this.setTitle("Chatfenster");
		this.username = username;
		buttonNewContact = new JButton("Kontakt Hinzufügen");
		buttonNewGroup   = new JButton("Gruppe erstellen");
		chatListPanel = new JPanel();
		chatListPanelLayout = new BoxLayout(chatListPanel, BoxLayout.PAGE_AXIS);
		chatListPanel.setLayout(chatListPanelLayout);
		chatListPanel.add(buttonNewContact);
		chatListPanel.add(buttonNewGroup);
		chatPanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chatListPanel, chatPanel);
		this.setTitle(TITLE_STRING);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		splitPane.setDividerLocation(DEFAULT_WIDTH/4);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(DEFAULT_WIDTH/4, DEFAULT_HEIGHT/4);
		chatListPanel.setMinimumSize(minimumSize);
		chatPanel.setMinimumSize(minimumSize);
		this.add(splitPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	
	/**
	 * returns the list of chats the current user is partaking in
	 * this includes group chats and direct chats
	 */
	public List<AbstractChat> getChatList(){
		return chatList;
	}
	/**
	 * adds a given chat (group or direct chat, doesn't matter) to the list of chats available in this window
	 */
	public void AddChat(AbstractChat newChat) {
		chatList.add(newChat);
		//TODO: append chat to the visual list too
	}
	
	/**
	 * receives an incoming message and puts it in the right chat history.
	 * @param message
	 */
	public void receiveMessage(Message message) {
		if(message.getGroupId()!=0) {
			//if group message:
			for(AbstractChat chat : chatList) {
				if( chat.getGroupID()==message.getGroupId()) {
					chat.appendNewMessageToHistory(message);
				}
			}
		}else {
			//if direct message:
			for(AbstractChat chat : chatList) {
				if(chat.getChatName()==message.getRecipient()) {
					chat.appendNewMessageToHistory(message);
				}
			}
		}
	}
	
	
}
