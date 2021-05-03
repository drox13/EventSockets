package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.dao.Answer;
import models.dao.EventManager;
import models.dao.Requests;
import models.entity.Concert;
import util.Json;

public class Connection implements Runnable{
	private Socket socket;
	private DataOutputStream outputConnection;
	private DataInputStream inputConnection;
	private boolean connectionStatus;
	private Thread theadRequests;
	private EventManager eventManager;
	private boolean user;

	public Connection(Socket socket, EventManager eventManager, boolean userType) throws IOException {
		this.socket = socket;
		this.eventManager = eventManager;
		user = userType;
		outputConnection = new DataOutputStream(this.socket.getOutputStream());
		inputConnection = new DataInputStream(socket.getInputStream());
		connectionStatus = true;
		theadRequests = new Thread(this);
		theadRequests.start();
		Logger.getGlobal().log(Level.INFO, "Nuevo coneccion establecida: " +
				socket.getInetAddress().getHostAddress());
	}

	@Override
	public void run() {
		while (connectionStatus) {
			try {
				if (inputConnection.available() > 0) {
					String request = inputConnection.readUTF();
					if(request!=null) {
						Logger.getGlobal().log(Level.INFO, "request server: " + request);
						switch (Requests.valueOf(request)) {
						case SENT_CONCERT:
							sentConcert();
							break;
						case VIEW_TICKETS:
							viewTickets();
							break;
						case CONFIRM_PURCHASE:
							confirmPurchase();
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void confirmPurchase() throws IOException {
		int idConcert = Integer.parseInt(Json.convertStringJsonToString(inputConnection.readUTF()));
		ArrayList<String> ticketsSelects = Json.convertStringtoArray(inputConnection.readUTF());
		boolean [] ticket = searhConcert(idConcert).getTickets();
		boolean allFree = checkAllFree(ticketsSelects, ticket);
		if(allFree) {
			for (String string: ticketsSelects) {
				ticket[Integer.parseInt(string)] =  true;
			}
			outputConnection.writeUTF(Answer.SUCCESSFUL.toString());
			outputConnection.writeUTF(Json.convertStringToStrigJson("transaccion Exitosa"));
		}else {
			outputConnection.writeUTF(Answer.FAIL.toString());
			outputConnection.writeUTF(Json.convertStringToStrigJson("transaccion Fallida"));
		}
	}

	private boolean checkAllFree(ArrayList<String> ticketsSelects, boolean[] ticket) {
		boolean allFree = false;
		for (String string: ticketsSelects) {
			if(ticket[Integer.parseInt(string)] == false) {
				allFree = true;
			}else{
				allFree = false;
				break;
			}
		}
		return allFree;
	}

	private void viewTickets() throws IOException {
		int idConcert = Integer.parseInt(Json.convertStringJsonToString(inputConnection.readUTF()));
		outputConnection.writeUTF(Answer.SEND_VECTOR_TICKETS.toString());
		outputConnection.writeUTF(Json.convertVectorToStringJson(searhTicketsByConcert(idConcert)));
		if(user) {
			outputConnection.writeUTF(Json.convertStringToStrigJson(String.valueOf(idConcert)));
		}
	}

	private void sentConcert() throws IOException {
		Concert newConcert = Json.stringtoJson(inputConnection.readUTF());
		eventManager.addConcert(newConcert);
		if(!user) {
			outputConnection.writeUTF(Answer.OK.toString());
		}
		eventManager.notifyClients("Nuevo Concieto creado", Json.convertConcertToStringJson(newConcert));
	}

	private Concert searhConcert(int id) {
		Iterator<Concert> iterator = eventManager.getConcerList();
		while(iterator.hasNext()) {
			Concert concert = iterator.next();
			if(concert.getId() == id) {
				return concert;
			}
		}
		throw new NullPointerException("no se encontro");
	}

	private boolean[] searhTicketsByConcert(int id) {
		Iterator<Concert> iterator = eventManager.getConcerList();
		while(iterator.hasNext()) {
			Concert concert = iterator.next();
			if(concert.getId() == id) {
				return concert.getTickets();
			}
		}
		throw new NullPointerException("no se encontro");
	}

	public void notifyConections(String message, String concertToJson) {
		try {
			outputConnection.writeUTF(Answer.NOTIFY_CONCERT_CLIENT.toString());
			outputConnection.writeUTF(message);
			outputConnection.writeUTF(concertToJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}