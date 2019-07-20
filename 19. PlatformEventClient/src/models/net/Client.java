package models.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import models.entity.Concert;
import observer.ManagerObserverWindow;
import util.JsonUtil;

public class Client {
	private static final int PORT = 3292;
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
					try {
						managerObserverWindow.notifyNewConcert(inputClient.readUTF());
						concertList.add(JsonUtil.convertStringToConcert(inputClient.readUTF()));
						managerObserverWindow.refreshConcertList(concertList);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		threadNotify.start();
	}
	
	public DataOutputStream getOutputClient() {
		return outputClient;
	}
	
	public DataInputStream getInputClient() {
		return inputClient;
	}
}