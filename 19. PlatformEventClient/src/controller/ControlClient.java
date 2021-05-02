package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.JButton;

import models.net.Client;
import models.net.RequestClient;
import observer.ManagerObserverWindow;
import util.JsonUtil;
import view.Command;
import view.WindowClient;

public class ControlClient implements ActionListener{
//	private static final String NO_TICKET_WAS_CHOSEN = "No se escogio ningun ticket";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			sendIdConcertAndTicket(idConcert, idTicket);
			break;
		case CONFIRM_PURCHASE:
			confirmPurchase();
			ticketsSelect.clear();
			break;
		case CANCEL_PURCHASE:
			ticketsSelect.clear();
			windowClient.closeDialog();
			break;
		}
	}
	
	private void confirmPurchase() throws NullPointerException{
			try {
				windowClient.closeDialog();
				client.getOutputClient().writeUTF(RequestClient.CONFIRM_PURCHASE.toString());
				client.getOutputClient().writeUTF(JsonUtil.convertStringToStrigJson(idConcert));
				client.getOutputClient().writeUTF(JsonUtil.convertArraytoStringJson(ticketsSelect));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	private boolean[] sendIdConcertAndTicket(String idConcert, String idTicket) throws NullPointerException{
		if(!idConcert.equals("")&& !idTicket.equals("")) {
			return client.buyTicketsbyConcert(Integer.parseInt(idConcert), Integer.parseInt(idTicket));
		}else {
			throw new NullPointerException("null");
		}
	}

	private void viewConcert(String id) {
		try {
			client.getOutputClient().writeUTF(Command.VIEW_TICKETS.toString());
			client.getOutputClient().writeUTF(JsonUtil.convertStringToStrigJson(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}