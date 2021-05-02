package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;

import models.entity.Concert;
import observer.ManagerObserverWindow;
import util.JsonUtil;
import view.WindowClient;

public class Client {
	private static final int PORT = 20987;
	private static final String HOST = "127.0.0.1";
	private Socket socket;
	private DataInputStream inputClient;
	private DataOutputStream outputClient;
	private Thread threadNotify;
	private boolean notifyOn;
	private ArrayList<Concert> concertList;
	private ManagerObserverWindow managerObserverWindow;

	public Client(ManagerObserverWindow managerObserverWindow) throws UnknownHostException, IOException {
		socket = new Socket(HOST,PORT);
		this.managerObserverWindow = managerObserverWindow;
		inputClient = new DataInputStream(socket.getInputStream());
		outputClient = new DataOutputStream(socket.getOutputStream());
		outputClient.writeUTF(RequestClient.CLIENT.toString());
		concertList = new ArrayList<>(JsonUtil.convertArrayJsonToArratConcert(inputClient.readUTF()));
		managerObserverWindow.refreshConcertList(concertList);
		notifyThis();
	}
	
	private void notifyThis() {
		notifyOn = true;
		threadNotify = new Thread(new Runnable() {
			@Override
			public void run() {
				while(notifyOn) {
					aswer();
				}
			}
		});
		threadNotify.start();
	}
	
	private void aswer() {
		try {
			String aswer = inputClient.readUTF();
			System.out.println("respuesta recibida en cliente: " + aswer);
			switch (AnswerClient.valueOf(aswer)) {
			case NOTIFY_CONCERT_CLIENT:
				managerObserverWindow.notifyNewConcert(inputClient.readUTF());
				concertList.add(JsonUtil.convertStringToConcert(inputClient.readUTF()));
				concertList.sort((o1, o2) -> o1.getDateFormat().compareTo(o2.getDateFormat()));
				managerObserverWindow.refreshConcertList(concertList);
				break;
			case SEND_VECTOR_TICKETS:
				boolean booleans [] = JsonUtil.convertStringJsontoVector(inputClient.readUTF());
				int idConsert = Integer.parseInt(JsonUtil.convertStringJsonToString(inputClient.readUTF()));
				Concert concert = searchConcert(idConsert);
				concert.setTickets(booleans);
				managerObserverWindow.fillDialog(booleans);
				break;
			case FAIL:
				WindowClient.showMessage(JsonUtil.convertStringJsonToString(inputClient.readUTF()));
				break;
			case SUCCESSFUL:
				WindowClient.showMessage(JsonUtil.convertStringJsonToString(inputClient.readUTF()));
//				managerObserverWindow.qr("un mensaje no importa");
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean[] buyTicketsbyConcert(int idConcert, int positionByVector)throws NullPointerException {
		for (Concert concert : concertList) {
			if(concert.getId() == idConcert) {
				concert.buyTicket(positionByVector);
				managerObserverWindow.fillDialog(concert.getTickets());
				return concert.getTickets();
			}
		}
		throw new NullPointerException("No se encontro el concierto");
	}
	
	public Concert searchConcert(int idConcert) throws NullPointerException{
		for (Concert concert : concertList) {
			if(concert.getId() == idConcert) {
				return concert;
			}
		}
		throw new NullPointerException("no se encontro");
	}
	
	public ArrayList<Concert> getConcertList() {
		return new ArrayList<>(concertList);
	}
	
	public DataOutputStream getOutputClient() {
		return outputClient;
	}
	
	public DataInputStream getInputClient() {
		return inputClient;
	}
}