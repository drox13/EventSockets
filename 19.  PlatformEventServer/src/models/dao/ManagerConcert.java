package models.dao;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;

import models.entity.Concert;
import queue_Stack_SimpleList.MyQueue;
import util.Json;

public class ManagerConcert {
	private MyQueue<Concert> concertList;
	
	public ManagerConcert() {
		concertList = new MyQueue<>(generateComparator());
		test();
	}
	
	private void test() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2021, 03, 30);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 03, 28);
		concertList.putToQueue(new Concert("segundo", 2, cal1));
		concertList.putToQueue(new Concert("primero", 2, cal2));
		System.out.println(concertList.showQueue());
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