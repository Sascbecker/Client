package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class ChatFrame extends JFrame {
	
	private static final String TITLE_STRING = null;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	ChatListPanel chatListPanel;
	JPanel chatPanel;
	JSplitPane splitPane;
	
	public ChatFrame() {
		chatListPanel = new ChatListPanel();
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
}
