package gui;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DateFormatter;

import logik.Message;

/**
 * JPanel that displays the date, sender and content of a message
 * @author alnenno
 *
 */
public class SingleMessagePanel extends JPanel {
	
	public SingleMessagePanel(Message message) {
		JTextArea textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setText( 
				(new SimpleDateFormat()).format(new Date(message.getTimestamp()))	//datum
				+" "+
				message.getSender()													//absender
				+": "+
				message.getMessage()												//nachricht
		);
		this.add(textarea);
	}
}
