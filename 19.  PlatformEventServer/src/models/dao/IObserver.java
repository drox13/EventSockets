package models.dao;

import java.util.Iterator;

import models.entity.Concert;

public interface IObserver {
	void notify(String message, String concertToJson);
	void addConcert(Concert concert);
	Iterator<Concert> getConcerList();
}