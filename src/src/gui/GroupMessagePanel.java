package gui;


import java.util.List;

import javax.swing.JPanel;

import logik.AbstractChat;
import logik.Group;
import logik.GroupChat;

public class GroupMessagePanel extends JPanel {
	private GroupChat chat;
	GroupUserPanel groupuserpanel;
	MessagePanel messagepanel;
	public GroupMessagePanel(AbstractChat chat) {
		this.chat = (GroupChat)chat;
		
	}
}
