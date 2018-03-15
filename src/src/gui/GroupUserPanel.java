package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logik.Group;

/**
 * panel that shows the user list in a group
 * @author alnenno
 *
 */
public class GroupUserPanel extends JPanel {
	
	Group group;
	JButton adduser;
	
	public GroupUserPanel(Group group) {
		this.group=group;
		adduser = new JButton("Nutzer Hinzufuegen");
		adduser.setBackground(Color.GREEN);
		setAddUserButtfunction();
		this.add(adduser);
		for(String user : group.getUserList()) {
			if(user.equals(group.getAdmin())) {
				JButton adminbutton = new JButton("ADMIN:"+user);
				adminbutton.setBackground(Color.yellow);
				this.add(adminbutton);
			}else {
				this.add(new JButton(user));
			}
		}
	}
	
	private void setAddUserButtfunction() {
		
	}

	public void addUser(String user) {
		
	}
	
	
}
