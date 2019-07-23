package views;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller.Command;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	public MenuBar(ActionListener actionListener) {
		JMenu menuFile = new JMenu("opciones");
		
		Item iCreateConcert = new Item("registrar concierto", Command.OPEN_DIALOG_CREATE_CONCERT.toString(), actionListener);
		menuFile.add(iCreateConcert);
		
		add(menuFile);
	}
}