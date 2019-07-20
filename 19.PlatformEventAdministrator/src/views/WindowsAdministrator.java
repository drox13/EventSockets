package views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.dao.Administrator;
import models.entity.Concert;

public class WindowsAdministrator extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelTable panelTable;

	public WindowsAdministrator(ActionListener actionListener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setExtendedState(MAXIMIZED_BOTH);
		setSize(200, 200);
		setJMenuBar(new MenuBar(actionListener));
		panelTable = new PanelTable();
		add(panelTable);
		setVisible(true);
	}

	public Concert createConcert() {
		String nameConcert = JOptionPane.showInputDialog(this, "nombre del nuevo concieto");
		int numberTickets = Integer.parseInt(JOptionPane.showInputDialog(this, "numero de voletas"));
		return Administrator.creatConcert(nameConcert, numberTickets);
	}

	public void filltable(ArrayList<Concert> concerts) {
		panelTable.fillTable(concerts);
	}

	public void messageOk(String message) {
		JOptionPane.showMessageDialog(this, message, "Registro ok", 
				JOptionPane.INFORMATION_MESSAGE);
	}
}