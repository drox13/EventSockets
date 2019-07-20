package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.dao.EventManager;
import models.dao.IObserver;
import models.entity.Concert;
import util.Json;

public class Server implements Runnable, IObserver{
	
	private static final int PORT = 3292;
	private ServerSocket serverSocket;
	private boolean serverOn;
	private Thread threadNewConnections;
	private Connection connectionsAmd;
	private ArrayList<Connection> connections;
	private ArrayList<Concert> concertList;
	private EventManager eventManager;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
		serverOn = true;
		threadNewConnections = new Thread(this);
		connections = new ArrayList<>();
		concertList = new ArrayList<>();
		threadNewConnections.start();
		eventManager = new EventManager(this);
		Logger.getGlobal().log(Level.INFO, "Servidor conect puerto 3010");
	}
	
	public static Concert createConcert(String name) {
		return new Concert(name);
	}
	
	public ArrayList<Concert> getListConcert() {
		return new ArrayList<>(concertList);
	}
	
	@Override
	public void run() {
		while (serverOn) {
			try {
				Socket socket = serverSocket.accept();
				DataInputStream inputConnection = new DataInputStream(socket.getInputStream());
				String conectionType = inputConnection.readUTF();
				switch (ConectionType.valueOf(conectionType)) {
				case ADMINISTRATOR:
					connectionsAmd = new Connection(socket, eventManager);
					break;
				case CLIENT:
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					Connection newConnection = new Connection(socket, eventManager);
					dataOutputStream.writeUTF(Json.convertArrayListToStringJson(concertList));
//					updateNewClient(concerts);
					connections.add(newConnection);	
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void notify(String message, String concertToJson) {
		for(Connection connection : connections) {
			connection.notifyConections(message, concertToJson);
		}
	}
	
	@Override
	public void addConcert(Concert concert) {
		concertList.add(concert);
	}

	@Override
	public ArrayList<Concert> getConcerList() {
		return getListConcert();
	}

}