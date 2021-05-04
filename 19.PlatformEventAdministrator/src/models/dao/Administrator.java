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

public class Administrator{
	
	private static final String REGISTER_CONCERT = "concierto registrado";
	private static final int PORT = 20987;
	private static final String HOST = "127.0.0.1";
	private ArrayList<Concert> concertList;
	private Socket socket;
	private DataOutputStream outputAdminitrator;
	private DataInputStream inputAdministrator;
	private String messageStatus;
	private ManagerObserver managerObserver;
	
	public Administrator(ManagerObserver managerObserver) throws UnknownHostException, IOException {
		this.managerObserver = managerObserver;
		socket = new Socket(HOST, PORT);
		outputAdminitrator = new DataOutputStream(socket.getOutputStream());
		inputAdministrator = new DataInputStream(socket.getInputStream());
		concertList = new ArrayList<>();
		initRequest();
	}

	private void initRequest() throws IOException {
		outputAdminitrator.writeUTF(RequestAdministrator.ADMINISTRATOR.toString());
		concertList = new ArrayList<>(Json.convertArrayJsonToArratConcert(inputAdministrator.readUTF()));
		managerObserver.updateTable(concertList);
	}

	public static Concert creatConcert(String name, int numberTickets, Calendar date) {
		return new Concert(name, numberTickets, date);
	}
	
	public ArrayList<Concert> getConcerts(){
		return new ArrayList<>(concertList);
	}
	
	@SuppressWarnings("unused")
	public void sendConcert(Concert concert) {
		try {
			outputAdminitrator.writeUTF(RequestAdministrator.SENT_CONCERT.toString());
			outputAdminitrator.writeUTF(Json.convertObjectJson(concert));
			String aswerAdm = inputAdministrator.readUTF();
				concertList.add(concert);
				concertList.sort((o1, o2) -> o1.getDateFormat().compareTo(o2.getDateFormat()));
				messageStatus = REGISTER_CONCERT;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessageStatus() {
		return messageStatus;
	}

	@SuppressWarnings("unused")
	public void requestViewTickets(String idConcert) {
		try {
			outputAdminitrator.writeUTF(RequestAdministrator.VIEW_TICKETS.toString());
			outputAdminitrator.writeUTF(Json.convertStringToStringJson(idConcert));
			String aswerAdm = inputAdministrator.readUTF();
			boolean booleans [] = Json.convertStringJsontoVector(inputAdministrator.readUTF());
			managerObserver.fillDialogTickets(booleans);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}