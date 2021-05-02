package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;

import models.dao.Administrator;
import observer.ManagerObserver;
import views.DialogRegisterConcert;
import views.WindowsAdministrator;

public class ControlAdministtrator implements ActionListener{
	private Administrator administrator;
	private WindowsAdministrator windowsAdministrator;
	private DialogRegisterConcert dialogRegisterConcert;
	private ManagerObserver managerObserver;
	
	public ControlAdministtrator() {
		windowsAdministrator = new WindowsAdministrator(this);
		managerObserver = new ManagerObserver(windowsAdministrator);
		try {
			administrator = new Administrator(managerObserver);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dialogRegisterConcert = new DialogRegisterConcert(windowsAdministrator, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case CREATE_CONCERT:
			createConcert();
			dialogRegisterConcert.setVisible(false);
			break;
		case OPEN_DIALOG_CREATE_CONCERT:
			dialogRegisterConcert.setVisible(true);
			break;
		case SHOW_CONCERT:
				administrator.requestViewTickets(((JButton)e.getSource()).getName());
//				administrator.getOutputAdminitrator().writeUTF(RequestAdministrator.VIEW_TICKETS.toString());
//				administrator.getOutputAdminitrator().writeUTF(
//						Json.convertStringToStringJson(((JButton)e.getSource()).getName()));
			break;

		}
	}
	
	private void createConcert() {
		administrator.sendConcert(dialogRegisterConcert.getConcert());
		refreshTable();
	}
	
	private void refreshTable() {
		windowsAdministrator.messageOk(administrator.getMessageStatus());
		windowsAdministrator.filltable(administrator.getConcerts());
	}
}
