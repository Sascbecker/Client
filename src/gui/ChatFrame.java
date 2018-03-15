package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import logik.AbstractChat;
import logik.Message;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class ChatFrame extends JFrame {
	
	private static final String TITLE_STRING = "Chatfenster";
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private JPanel chatListPanel;
	private List<JPanel> chatListPanels;
	private LayoutManager chatListPanelLayout;
	private JPanel chatPanel;
	private JSplitPane splitPane;
	private JButton buttonNewContact;
	private JButton buttonNewGroup;
	private JPanel leftPanel;
	private LayoutManager leftLayout;

	
	private String username;
	private List<AbstractChat> chatList;
	private Thread chatUpdaterDaemon;
	
	public ChatFrame(String username) {
		this.setTitle(TITLE_STRING);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.username = username;
		this.chatListPanels = new ArrayList<JPanel>();
		this.chatList = new ArrayList<AbstractChat>();
		
		assembleWindowComponents();
		setButtonFunctions();	
		launchChatUpdaterDaemon();
		
		
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(DEFAULT_WIDTH/4, DEFAULT_HEIGHT/4);
		chatListPanel.setMinimumSize(minimumSize);
		chatPanel.setMinimumSize(minimumSize);
		this.add(splitPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("window constructor finished");

	}
	
	/**
	 * regularly updates the chatlist in the gui with the information in the logical chatlist
	 */
	private void launchChatUpdaterDaemon() {
		chatUpdaterDaemon= new Thread(new Runnable() {
			public void run() {
				while(true) {
					updateChatListPanel();
					mySleep(2_000);
				}
			}

		});
		chatUpdaterDaemon.start();
		
	}
	private void updateChatListPanel() {
		if(chatList!=null) {
			leftPanel.remove(chatListPanel);
			this.chatListPanels = new ArrayList<JPanel>();
			for(AbstractChat chat : chatList) {
				chatListPanels.add(panelize(chat));
			}
			chatListPanel = new JPanel();
			chatListPanelLayout = new BoxLayout(chatListPanel, BoxLayout.PAGE_AXIS);
			chatListPanel.setLayout(chatListPanelLayout);
			for(JPanel panel : chatListPanels) {
				chatListPanel.add(panel);
			}
			leftPanel.add(chatListPanel);
		}
	}
	

	/**
	 * turns a chat object into a JPanel which can be placed in the ChatListPanel
	 * @return
	 */
	private JPanel panelize(final AbstractChat chat) {
		JPanel returnvalue = new JPanel();
		returnvalue.add(new JLabel(chat.getChatName()+"("+chat.getUnreadMessages()+")"));
		returnvalue.addMouseListener(new MouseListener() {
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
				if(chat.getGroupID()==0) {//dm chat
					chatPanel=new GroupMessagePanel(chat);
				}else {//group chat
					chatPanel=new DirectMessagePanel(chat);
				}
			}
		});
		return returnvalue;
	}

	private void assembleWindowComponents() {
		buttonNewContact = new JButton("Kontakt Hinzufügen");
		buttonNewGroup   = new JButton("Gruppe erstellen");
		leftPanel = new JPanel();
		leftLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		chatListPanel = new JPanel();
		chatListPanelLayout = new BoxLayout(chatListPanel, BoxLayout.Y_AXIS);
		chatListPanel.setLayout(chatListPanelLayout);
		leftPanel.add(buttonNewContact);
		leftPanel.add(buttonNewGroup);
		leftPanel.add(chatListPanel);
		chatPanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, chatPanel);
		
		splitPane.setDividerLocation(DEFAULT_WIDTH/4);
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
	
	/**
	 * sets the action listeners for all statically present buttons
	 * currently addContact and createGroup
	 */
	private void setButtonFunctions() {
		buttonNewContact.addMouseListener(new MouseListener() {
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
				String contactName = JOptionPane.showInputDialog("kontaktname eingeben");
				if(contactName != null && contactName.length()>0) {
					//TODO: send contact call to server
				}
			}
		});
		buttonNewGroup.addMouseListener(new MouseListener() {
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
				String groupName = JOptionPane.showInputDialog("gruppenname eingeben");
				if(groupName != null && groupName.length()>0) {
					//TODO: send group call to server
				}
			}
		});
	}
	
	private void mySleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//NOBODY CARES
		}
	}
	
}
