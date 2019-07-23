package views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.entity.Concert;

public class WindowsAdministrator extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelTable panelTable;

	public WindowsAdministrator(ActionListener actionListener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		setJMenuBar(new MenuBar(actionListener));
		panelTable = new PanelTable();
		add(panelTable);
		setVisible(true);
	}

	public void filltable(ArrayList<Concert> concerts) {
		panelTable.fillTable(concerts);
	}

	public void messageOk(String message) {
		JOptionPane.showMessageDialog(this, message, "Registro ok", 
				JOptionPane.INFORMATION_MESSAGE);
	}
}