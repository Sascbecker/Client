package gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import logik.Message;

/**
 * panel that displays the messages in either a group chat or a direct user chat
 * @author alnenno
 *
 */
public class MessagePanel extends JPanel {
	List<Message> history;
	JScrollPane scrollpane;
	JPanel messageHistory;
	JPanel messageSending;
	JSplitPane splitpane;
	
	
	
	public MessagePanel(List<Message> history) {
		this.history = history;
		messageHistory.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		scrollpane = new JScrollPane(this);
		for(Message message : history) {
			messageHistory.add(new SingleMessagePanel(message));
		}
		//TODO: complete window construction
		splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageHistory, messageSending);
	}
	public void appendNewMessagesToHistory(List<Message> newMessages) {
		history.addAll(newMessages);
		for(Message message : newMessages) {
			messageHistory.add(new SingleMessagePanel(message));
		}
	}


}
