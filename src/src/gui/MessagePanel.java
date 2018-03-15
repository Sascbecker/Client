package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import logik.AbstractChat;
import logik.Message;
import rest.ClientConnector;

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
	JButton sendbutton;
	JTextField sendField;
	AbstractChat chat;
	String sender;
	
	
	public MessagePanel(List<Message> history, AbstractChat chat, String sender) {
		this.history = history;
		this.chat = chat;
		this.sender=sender;
		this.messageHistory=new JPanel();
		//messageHistory.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		if(history!=null) {
			for(Message message : history) {
				messageHistory.add(new SingleMessagePanel(message));
			}
			//TODO: complete window construction
			messageHistory.setPreferredSize(new Dimension(400,2000));
			scrollpane = new JScrollPane(messageHistory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			scrollpane.setPreferredSize(new Dimension(400,200));
			messageSending=new JPanel();
			messageSending.setPreferredSize(new Dimension(400, 200));
			sendField = new JTextField();
			sendField.setPreferredSize(new Dimension(200,100));
			messageSending.add(sendField);
			sendbutton= new JButton("senden");
			sendbutton.setPreferredSize(new Dimension(100, 100));
			setbuttonListener();
			messageSending.add(sendbutton);
			//splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageHistory, messageSending);
			splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, messageSending);

			this.add(splitpane);
		}else {
			System.out.println("history is null");
		}
	}
	private void setbuttonListener() {
		this.sendbutton.addMouseListener(new MouseListener() {
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
				int groupId=chat.getGroupID();
				String recipient;
				String message = sendField.getText();
				long timestamp = System.currentTimeMillis();
				int action=0;
				if(chat.getGroupID()==0) {
					recipient=chat.getChatName();
				}else {
					recipient="";
				}
				try {
					ClientConnector.sendMessage(new dataclass.Message(sender, groupId, recipient, message, timestamp));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
	public void appendNewMessagesToHistory(List<Message> newMessages) {
		history.addAll(newMessages);
		for(Message message : newMessages) {
			messageHistory.add(new SingleMessagePanel(message));
		}
	}


}
