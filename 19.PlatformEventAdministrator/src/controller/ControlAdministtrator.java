package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import models.dao.Administrator;
import views.DialogRegisterConcert;
import views.WindowsAdministrator;

public class ControlAdministtrator implements ActionListener{
	private Administrator administrator;
	private WindowsAdministrator windowsAdministrator;
	private DialogRegisterConcert dialogRegisterConcert;
	
	public ControlAdministtrator() {
		try {
			administrator = new Administrator();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		windowsAdministrator = new WindowsAdministrator(this);
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
