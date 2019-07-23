package models.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;

import models.entity.Concert;
import util.Json;

public class Administrator {
	
	private static final String REGISTER_CONCERT = "concierto registrado";
	private static final int PORT = 3292;
	private static final String HOST = "127.0.0.1";
	private ArrayList<Concert> concertList;
	private Socket socket;
	private DataOutputStream outputAdminitrator;
	private DataInputStream inputAdministrator;
	private String messageStatus;
	
	public Administrator() throws UnknownHostException, IOException {
		socket = new Socket(HOST, PORT);
		outputAdminitrator = new DataOutputStream(socket.getOutputStream());
		inputAdministrator = new DataInputStream(socket.getInputStream());
		concertList = new ArrayList<>();
		outputAdminitrator.writeUTF(RequestAdministrator.ADMINISTRATOR.toString());
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
			switch (AswerAdm.valueOf(aswerAdm)) {
			case OK:
				concertList.add(concert);
				messageStatus = REGISTER_CONCERT;
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
}