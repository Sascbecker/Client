package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import logik.AbstractChat;
import logik.GroupChat;
import logik.Message;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

public class ChatFrame extends JFrame {

	private static final String TITLE_STRING = "Chatfenster";
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;

	private JPanel chatListPanel;
	private List<JButton> chatListPanels;
	private LayoutManager chatListPanelLayout;
	private JPanel chatPanel;
	private JSplitPane splitPane;
	private JButton buttonNewContact;
	private JButton buttonNewGroup;
	private JPanel leftPanel;
	private JScrollPane leftScrollPane;
	private LayoutManager leftLayout;
	private JPanel rightPanel;

	private String username;
	private List<AbstractChat> chatList;
	private Thread chatUpdaterDaemon;

	public ChatFrame(String username) {
		this.setTitle(TITLE_STRING);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.username = username;
		this.chatListPanels = new ArrayList<JButton>();
		this.chatList = new ArrayList<AbstractChat>();

		assembleWindowComponents();
		setButtonFunctions();
		launchChatUpdaterDaemon();

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4);
		chatListPanel.setMinimumSize(minimumSize);
		chatPanel.setMinimumSize(minimumSize);
		this.add(splitPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("window constructor finished");

	}

	/**
	 * regularly updates the chatlist in the gui with the information in the logical
	 * chatlist
	 */
	private void launchChatUpdaterDaemon() {
		chatUpdaterDaemon = new Thread(new Runnable() {
			public void run() {
				while (true) {
					updateChatListPanel();
					mySleep(2_000);
				}
			}

		});
		chatUpdaterDaemon.start();

	}

	private void updateChatListPanel() {
		if (chatList != null && leftPanel != null) {
			leftPanel.remove(chatListPanel);
			this.chatListPanels = new ArrayList<JButton>();
			for (AbstractChat chat : chatList) {
				chatListPanels.add(panelize(chat));
			}
			chatListPanel = new JPanel();
			chatListPanelLayout = new BoxLayout(chatListPanel, BoxLayout.PAGE_AXIS);
			chatListPanel.setLayout(chatListPanelLayout);
			for (JButton panel : chatListPanels) {
				chatListPanel.add(panel);

			}
			leftPanel.add(chatListPanel);
			leftPanel.validate();
		}
	}

	/**
	 * turns a chat object into a JPanel which can be placed in the ChatListPanel
	 * 
	 * @return
	 */
	private JButton panelize(final AbstractChat chat) {
		JButton returnvalue = new JButton(chat.getChatName() + "(" + chat.getUnreadMessages() + ")");
		returnvalue.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}// do nothing

			@Override
			public void mousePressed(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseExited(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseEntered(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(chat.getChatName());
				chat.readMessages();
				if (chat.getGroupID() != 0) {// group chat
					chatPanel = new GroupMessagePanel(chat, username);
				} else {// dm chat
					chatPanel = new DirectMessagePanel(chat, username);
				}
				if (chatPanel == null) {
					System.out.println("aaaa");
				} else {
					rightPanel.removeAll();
					rightPanel.add(chatPanel);
					chatPanel.validate();
				}
				rightPanel.validate();

			}
		});
		return returnvalue;
	}

	private void assembleWindowComponents() {
		buttonNewContact = new JButton("Kontakt Hinzufügen");
		buttonNewGroup = new JButton("Gruppe erstellen");
		chatListPanel = new JPanel();
		chatListPanelLayout = new BoxLayout(chatListPanel, BoxLayout.Y_AXIS);
		chatListPanel.setLayout(chatListPanelLayout);
		chatPanel = new JPanel();
		rightPanel = new JPanel();

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 20_000));
		leftPanel.add(buttonNewContact);
		leftPanel.add(buttonNewGroup);
		leftPanel.add(chatListPanel);
		leftScrollPane= new JScrollPane(
						leftPanel, 
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER		
				);
		
		rightPanel.add(chatPanel);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightPanel);

		splitPane.setDividerLocation(DEFAULT_WIDTH / 4);
	}

	/**
	 * returns the list of chats the current user is partaking in this includes
	 * group chats and direct chats
	 */
	public List<AbstractChat> getChatList() {
		return chatList;
	}

	/**
	 * adds a given chat (group or direct chat, doesn't matter) to the list of chats
	 * available in this window
	 */
	public void AddChat(AbstractChat newChat) {
		chatList.add(newChat);
	}

	/**
	 * receives an incoming message and puts it in the right chat history.
	 * 
	 * @param message
	 */
	public void receiveMessage(Message message) {
		if (message.getGroupId() != 0) {
			// if group message:
			for (AbstractChat chat : chatList) {
				if (chat.getGroupID() == message.getGroupId()) {
					chat.appendNewMessageToHistory(message);
				}
			}
		} else {
			// if direct message:
			for (AbstractChat chat : chatList) {
				System.out.println("chatname: "+ chat.getChatName() + " nachrichtensender: " + message.getSender());
				if (chat.getChatName() == message.getSender()) {
					chat.appendNewMessageToHistory(message);
					System.out.println("message an chat " +chat.getChatName()+" angehangen");
				}
			}
		}
	}

	/**
	 * sets the action listeners for all statically present buttons currently
	 * addContact and createGroup
	 */
	private void setButtonFunctions() {
		buttonNewContact.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}// do nothing

			@Override
			public void mousePressed(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseExited(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseEntered(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseClicked(MouseEvent e) {
				String contactName = JOptionPane.showInputDialog("kontaktname eingeben");
				if (contactName != null && contactName.length() > 0) {
					// TODO: send contact call to server
				}
			}
		});
		buttonNewGroup.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}// do nothing

			@Override
			public void mousePressed(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseExited(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseEntered(MouseEvent e) {
			}// do nothing

			@Override
			public void mouseClicked(MouseEvent e) {
				String groupName = JOptionPane.showInputDialog("gruppenname eingeben");
				if (groupName != null && groupName.length() > 0) {
					// TODO: send group call to server
				}
			}
		});
	}

	private void mySleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// NOBODY CARES
		}
	}

}
