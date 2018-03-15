package gui;


import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import logik.AbstractChat;
import logik.ContactChat;
import logik.Group;
import logik.GroupChat;

public class GroupMessagePanel extends JPanel {
	private GroupChat chat;
	GroupUserPanel groupuserpanel;
	MessagePanel messagepanel;
	JScrollPane scrollpane;
	JSplitPane splitpane;
	
	public GroupMessagePanel(AbstractChat chat, String user) {
		this.chat = (GroupChat)chat;
		this.messagepanel=new MessagePanel(chat.getHistory(),chat,user);

		Group group=new Group(
				this.chat.getAdmin(), 
				this.chat.getUsers(), 
				this.chat.getHistory()
				);
		
		this.groupuserpanel = new GroupUserPanel(group);
		groupuserpanel.setPreferredSize(new Dimension(400,2000));
		scrollpane = new JScrollPane(groupuserpanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollpane.setPreferredSize(new Dimension(400,200));
		splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, messagepanel);
		
		this.add(splitpane);		
	}
}
