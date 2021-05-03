package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.dao.EventManager;
import models.dao.IObserver;
import models.entity.Concert;
import queue_Stack_SimpleList.MyQueue;
import util.Json;

public class Server implements Runnable, IObserver{
	
	private static final int PORT = 20987;
	private ServerSocket serverSocket;
	private boolean serverOn;
	private Thread threadNewConnections;
	private Connection connectionsAmd;
	private ArrayList<Connection> connections;
	private MyQueue<Concert> concertList;
	private EventManager eventManager;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
		serverOn = true;
		threadNewConnections = new Thread(this);
		connections = new ArrayList<>();
		concertList = new MyQueue<>(generateComparator());
		threadNewConnections.start();
		eventManager = new EventManager(this);
		Logger.getGlobal().log(Level.INFO, "Servidor conect puerto " + PORT);
//		test();
	}

	private Comparator<Concert> generateComparator() {
		Comparator<Concert> comparator = new Comparator<Concert>() {

			@Override
			public int compare(Concert o1, Concert o2) {
				return o1.getDateFormat().compareTo(o2.getDateFormat());
			}
		};
		return comparator;
	}
	
	private void test() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2021, 03, 30);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 03, 28);
		concertList.putToQueue(new Concert("segundo", 2, cal1));
		concertList.putToQueue(new Concert("primero", 2, cal2));
		System.out.println(concertList.showQueue());
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
					connectionsAmd = new Connection(socket, eventManager, false);
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

	private void newClient(Socket socket) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		Connection newConnection = new Connection(socket, eventManager, true);
		dataOutputStream.writeUTF(Json.convertArrayListToStringJson(concertList));
		connections.add(newConnection);
	}

	@Override
	public void notify(String message, String concertToJson) {
		for(Connection connection : connections) {
			connection.notifyConections(message, concertToJson);
		}
	}
	
	@Override
	public void addConcert(Concert concert) {
		concertList.putToQueue(concert);
	}

	@Override
	public Iterator<Concert> getConcerList() {
		return concertList.iterator();
	}
	
	public Connection getConnectionsAmd() {
		return connectionsAmd;
	}
}