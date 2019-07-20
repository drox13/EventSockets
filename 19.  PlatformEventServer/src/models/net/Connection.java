package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
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
						System.out.println(request);
						switch (Requests.valueOf(request)) {
						case SENT_CONCERT:
							Concert newConcert = Json.stringtoJson(inputConnection.readUTF());
							eventManager.addConcert(newConcert);
							if(user) {
								outputConnection.writeUTF(Answer.NOTIFY_CONCERT_CLIENT.toString());
								eventManager.notifyClients("Nuevo Concieto creado", Json.convertConcertToStringJson(newConcert));
							}else {
								outputConnection.writeUTF(Answer.OK.toString());
							}
							break;
						case VIEW_CONCERT:
							int idConcert = Integer.parseInt(Json.convertStringJsonToString(inputConnection.readUTF()));
							if(user) {
								outputConnection.writeUTF(Answer.SEND_VECTOR_TICKETS.toString());
								outputConnection.writeUTF(Json.convertVectorToStringJson(searhConcert(idConcert)));
							}
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean[] searhConcert(int id) {
		ArrayList<Concert> copyConcertServer = eventManager.getConcerList();
		for (Concert concert : copyConcertServer) {
			if(concert.getId() == id) {
				return concert.getTickets();
			}
		}
		throw new NullPointerException("no se encontro");
	}

	public void notifyConections(String message, String concertToJson) {
		try {
			outputConnection.writeUTF(message);
			outputConnection.writeUTF(concertToJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
