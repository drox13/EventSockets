package models.entity;

import java.util.Calendar;

import util.CalendarUtil;

public class Concert {
	private int id;
	private static int count;
	private String name;
	private boolean [] tickets;
	private int numberTickets;
	private Calendar date;
	
	public Concert(String name, int numberTickets, Calendar date) {
		this.name = name;
		this.numberTickets = numberTickets;
		tickets = new boolean[numberTickets];
		id = ++count;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return id + name;
	}
	
	public boolean[] getTickets() {
		return tickets;
	}
	
	public Object[] toVectorTable() {
		return new Object[] {id, name, numberTickets, CalendarUtil.toStringCalendar(date)};
	}
}
