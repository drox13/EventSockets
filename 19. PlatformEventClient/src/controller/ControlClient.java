package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import models.net.Client;
import observer.ManagerObserverWindow;
import view.Command;
import view.WindowClient;

public class ControlClient implements ActionListener{
	private static final String SERVER_OFF = "Servidor fuera de linea";
	private static final String NULL_MESSAGE = "null";
	
	private Client client;
	private WindowClient windowClient;
	private ManagerObserverWindow managerObserverWindow;
	private String idConcert; 
	private String idTicket;
	private ArrayList<String> ticketsSelect = new ArrayList<>();

	public ControlClient() {
		windowClient = new WindowClient(this);
		managerObserverWindow = new ManagerObserverWindow(windowClient);
		try {
			client = new Client(managerObserverWindow);
		} catch (IOException e) {
			WindowClient.showMessage(SERVER_OFF);
			System.exit(0);
		}
		ticketsSelect = new ArrayList<>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Command.valueOf(e.getActionCommand())) {
		case VIEW_TICKETS:
			idConcert = ((JButton)e.getSource()).getName();
			viewConcert(idConcert);
			break;
		case SEND_ID_CONCERT_AND_TICKET:
			idTicket = ((JButton)e.getSource()).getName();
			ticketsSelect.add(idTicket);
			selectTicket(idConcert, idTicket);
			break;
		case CONFIRM_PURCHASE:
			confirmPurchase();
			ticketsSelect.clear();
			break;
		case CANCEL_PURCHASE:
			cancelPurchase();
			break;
		}
	}

	private void cancelPurchase() {
		ticketsSelect.clear();
		windowClient.closeDialog();
	}
	
	private void confirmPurchase() throws NullPointerException{
			try {
				windowClient.closeDialog();
				client.sendConfirmPurchase(idConcert, ticketsSelect );
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private void selectTicket(String idConcert, String idTicket) throws NullPointerException{
		if(!idConcert.equals("")&& !idTicket.equals("")) {
			client.selectTicketsbyConcert(Integer.parseInt(idConcert), Integer.parseInt(idTicket));
		}else {
			throw new NullPointerException(NULL_MESSAGE);
		}
	}

	private void viewConcert(String id) {
		try {
			client.sendRequestViewConcert(Command.VIEW_TICKETS.toString(), id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}