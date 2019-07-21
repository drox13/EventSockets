package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import models.net.Client;
import observer.ManagerObserverWindow;
import util.JsonUtil;
import view.Command;
import view.WindowClient;

public class ControlClient implements ActionListener{
	private Client client;
	private WindowClient windowClient;
	private ManagerObserverWindow managerObserverWindow;
	private String idConcert = ""; 

	public ControlClient() {
		windowClient = new WindowClient(this);
		managerObserverWindow = new ManagerObserverWindow(windowClient);
		try {
			client = new Client(managerObserverWindow);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		switch (Command.valueOf(e.getActionCommand())) {
		case VIEW_CONCERT:
			idConcert = ((JButton)e.getSource()).getName();
			viewConcert(idConcert);
			break;
		case SEND_ID_CONCERT_AND_TICKET:
			System.out.println( "ctl c: line 40: " + idConcert);
			sendIdConcertAndTicket(idConcert, ((JButton)e.getSource()).getName());
			break;
		}
	}

	private void sendIdConcertAndTicket(String idConcert, String idTicket) {
		client.searchConcert(Integer.parseInt(idConcert), Integer.parseInt(idTicket));
	}

	private void viewConcert(String id) {
		try {
			client.getOutputClient().writeUTF(Command.VIEW_CONCERT.toString());
			client.getOutputClient().writeUTF(JsonUtil.convertStringToStrigJson(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}