package views;

import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import controller.Command;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	public MenuBar(ActionListener actionListener) {
		Item iCreateConcert = new Item("create concert", Command.CREATE_CONCERT.toString(), actionListener);
		add(iCreateConcert);
	}
}