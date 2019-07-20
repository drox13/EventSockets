package models.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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

	public static Concert creatConcert(String name, int numberTickets) {
		return new Concert(name, numberTickets);
	}
	
	public ArrayList<Concert> getConcerts(){
		return new ArrayList<>(concertList);
	}
	
	public void sendConcert(Concert concert) {
		try {
			outputAdminitrator.writeUTF(RequestAdministrator.SENT_CONCERT.toString());
			outputAdminitrator.writeUTF(Json.convertObjectJson(concert));
			if(inputAdministrator.readUTF().equals("ok")) {
				concertList.add(concert);
				messageStatus = REGISTER_CONCERT;
			}else {
				
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