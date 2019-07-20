package views;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class Item extends JMenuItem{

	private static final long serialVersionUID = 1L;
	
	public Item(String nameItem, String command, ActionListener actionListener) {
		setText(nameItem);
		setActionCommand(command);
		addActionListener(actionListener);
	}
}