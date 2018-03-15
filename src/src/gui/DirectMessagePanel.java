package gui;

import javax.swing.JPanel;

import logik.AbstractChat;
import logik.ContactChat;

public class DirectMessagePanel extends JPanel {
	private ContactChat chat;
	MessagePanel messagepanel;

	public DirectMessagePanel(AbstractChat chat, String user) {
		this.chat=(ContactChat)chat;
		this.messagepanel=new MessagePanel(chat.getHistory(),chat,user);
		this.add(messagepanel);
	}

}
