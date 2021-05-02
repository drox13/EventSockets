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
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean[] getTickets() {
		return tickets;
	}
	
	public String getDateFormat() {
		return CalendarUtil.toStringCalendar(date);
	}
	
	public void setTickets(boolean[] tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return "concierto: " + id +" "+ name + " " +" "+ numberTickets +" " + getDateFormat();
	}
}