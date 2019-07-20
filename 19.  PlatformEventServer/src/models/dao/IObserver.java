package models.dao;

import java.util.ArrayList;

import models.entity.Concert;

public interface IObserver {
	void notify(String message, String concertToJson);
	void addConcert(Concert concert);
	ArrayList<Concert> getConcerList();
}
