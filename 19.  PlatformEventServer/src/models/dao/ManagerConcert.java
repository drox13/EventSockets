package models.dao;

import java.util.Comparator;
import java.util.Iterator;

import models.entity.Concert;
import queue_Stack_SimpleList.MyQueue;
import util.Json;

public class ManagerConcert {
	private MyQueue<Concert> concertList;
	
	public ManagerConcert() {
		concertList = new MyQueue<>(generateComparator());
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
	
	public void addConcert(Concert concert) {
		concertList.putToQueue(concert);
	}
	
	public String getListtoString() {
		return Json.convertArrayListToStringJson(concertList);
	}
	
	public Iterator<Concert> getIterator() {
		return concertList.iterator();
	}
}