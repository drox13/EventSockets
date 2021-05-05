package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.dao.EventManager;
import models.dao.IObserver;
import models.dao.ManagerConcert;
import models.entity.Concert;

public class Server implements Runnable, IObserver{
	
	private static final int PORT = 20987;
	private ServerSocket serverSocket;
	private boolean serverOn;
	private Thread threadNewConnections;
	private ArrayList<Connection> connections;
	private EventManager eventManager;
	private ManagerConcert managerConcert;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
		serverOn = true;
		threadNewConnections = new Thread(this);
		connections = new ArrayList<>();
		threadNewConnections.start();
		eventManager = new EventManager(this);
		managerConcert = new ManagerConcert();
		Logger.getGlobal().log(Level.INFO, "Servidor conect puerto " + PORT);
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
					administrator(socket);
					break;
				case CLIENT:
					newClient(socket);	
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void administrator(Socket socket) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		new Connection(socket, eventManager, false);
		dataOutputStream.writeUTF(managerConcert.getListtoString());
	}

	private void newClient(Socket socket) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		Connection newConnection = new Connection(socket, eventManager, true);
		dataOutputStream.writeUTF(managerConcert.getListtoString());
		connections.add(newConnection);
	}

	@Override
	public void notify(String message, String concertToJson) {
		for(Connection connection : connections) {
			connection.notifyConection(message, concertToJson);
		}
	}
	
	@Override
	public void addConcert(Concert concert) {
		managerConcert.addConcert(concert);
	}

	@Override
	public Iterator<Concert> getConcerList() {
		return managerConcert.getIterator();
	}

	@Override
	public void removeConections() {
		connections.clear();
	}
}