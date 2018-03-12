package gui;


import java.util.List;

import javax.swing.JPanel;

import logik.Group;

public class GroupPanel extends JPanel {
	private Group group;
	GroupUserPanel groupuserpanel;
	MessagePanel messagepanel;
	public GroupPanel(Group group) {
		this.group = group;
		
	}
}
