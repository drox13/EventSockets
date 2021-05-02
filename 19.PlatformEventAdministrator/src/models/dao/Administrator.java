package models.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;

import models.entity.Concert;
import observer.ManagerObserver;
import util.Json;

public class Administrator implements Runnable{
	
	private static final String REGISTER_CONCERT = "concierto registrado";
	private static final int PORT = 20987;
	private static final String HOST = "127.0.0.1";
	private ArrayList<Concert> concertList;
	private Socket socket;
	private DataOutputStream outputAdminitrator;
	private DataInputStream inputAdministrator;
	private String messageStatus;
	private ManagerObserver managerObserver;
	private Thread threadAswer;
	private boolean onAswer;
	
	public Administrator(ManagerObserver managerObserver) throws UnknownHostException, IOException {
		this.managerObserver = managerObserver;
		socket = new Socket(HOST, PORT);
		outputAdminitrator = new DataOutputStream(socket.getOutputStream());
		inputAdministrator = new DataInputStream(socket.getInputStream());
		concertList = new ArrayList<>();
		outputAdminitrator.writeUTF(RequestAdministrator.ADMINISTRATOR.toString());
		
		onAswer = true;
		threadAswer = new Thread(this);
//		threadAswer.start();
	}

	public static Concert creatConcert(String name, int numberTickets, Calendar date) {
		return new Concert(name, numberTickets, date);
	}
	
	public ArrayList<Concert> getConcerts(){
		return new ArrayList<>(concertList);
	}
	
	public void sendConcert(Concert concert) {
		try {
			outputAdminitrator.writeUTF(RequestAdministrator.SENT_CONCERT.toString());
			outputAdminitrator.writeUTF(Json.convertObjectJson(concert));
			String aswerAdm = inputAdministrator.readUTF();
			System.out.println("respuesta resivida admi:" + aswerAdm);
//			if(aswerAdm.equals("OK")) {
				concertList.add(concert);
				concertList.sort((o1, o2) -> o1.getDateFormat().compareTo(o2.getDateFormat()));
				messageStatus = REGISTER_CONCERT;
//			}
//			
//			switch (AswerAdm.valueOf(aswerAdm)) {
//			case OK:
//				concertList.add(concert);
//				concertList.sort((o1, o2) -> o1.getDateFormat().compareTo(o2.getDateFormat()));
//				messageStatus = REGISTER_CONCERT;
//				break;
//			case SEND_VECTOR_TICKETS:
//				boolean booleans [] = Json.convertStringJsontoVector(inputAdministrator.readUTF());
//				managerObserver.fillDialogTickets(booleans);
//				break;
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(onAswer) {
			aswer();
		}
	}
	
	private void aswer() {
		try {
			String aswerAdm = inputAdministrator.readUTF();
			System.out.println("respuesta resivida admi:" + aswerAdm);
			switch (AswerAdm.valueOf(aswerAdm)) {
			case SEND_VECTOR_TICKETS:
				boolean booleans [] = Json.convertStringJsontoVector(inputAdministrator.readUTF());
				managerObserver.fillDialogTickets(booleans);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMessageStatus() {
		return messageStatus;
	}

	public void requestViewTickets(String idConcert) {
		try {
			outputAdminitrator.writeUTF(RequestAdministrator.VIEW_TICKETS.toString());
			outputAdminitrator.writeUTF(Json.convertStringToStringJson(idConcert));
			
			String aswerAdm = inputAdministrator.readUTF();
			System.out.println("respuesta resivida admi:" + aswerAdm);
			boolean booleans [] = Json.convertStringJsontoVector(inputAdministrator.readUTF());
			managerObserver.fillDialogTickets(booleans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}