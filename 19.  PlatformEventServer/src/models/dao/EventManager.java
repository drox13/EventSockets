package models.dao;

import java.util.ArrayList;

import models.entity.Concert;

public class EventManager {
	private IObserver serverObserver;
	
	public EventManager(IObserver serverObserve) {
		this.serverObserver = serverObserve;
	}
	
	public void notifyClients(String message, String concertToJson) {
		serverObserver.notify(message, concertToJson);
	}
	
	public void addConcert(Concert concert) {
		serverObserver.addConcert(concert);
	}
	
	public ArrayList<Concert> getConcerList() {
		return serverObserver.getConcerList();
	}
}