package gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logik.Message;

/**
 * panel that displays the messages in either a group chat or a direct user chat
 * @author alnenno
 *
 */
public class MessagePanel extends JPanel {
	List<Message> history;
	JScrollPane scrollpane;
	
	
	
	public MessagePanel(List<Message> history) {
		this.history = history;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		scrollpane = new JScrollPane(this);
		for(Message message : history) {
			this.add(new SingleMessagePanel(message));
		}
		
	}
	public void appendNewMessagesToHistory(List<Message> newMessages) {
		history.addAll(newMessages);
		for(Message message : newMessages) {
			this.add(new SingleMessagePanel(message));
		}
	}


}
