package gui;

import javax.swing.JPanel;

import logik.AbstractChat;
import logik.ContactChat;

public class DirectMessagePanel extends JPanel {
	private ContactChat chat;
	public DirectMessagePanel(AbstractChat chat) {
		this.chat=(ContactChat)chat;
	}

}
