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
	public static int number;
	
	public Concert(String name, int numberTickets, Calendar date) {
		this.name = name;
		this.numberTickets = numberTickets;
		tickets = new boolean[numberTickets];
		id = ++count;
		number = numberTickets;
		this.date = date;
	}
	
	public void buyTicket(int positionByVector) {
		tickets[positionByVector] = true;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberTickets() {
		return numberTickets;
	}
	
	public String getDateFormat() {
		return CalendarUtil.toStringCalendar(date);
	}
	
	public void setTickets(boolean[] tickets) {
		this.tickets = tickets;
	}
	
	public boolean[] getTickets() {
		return tickets;
	}
	
	@Override
	public String toString() {
		return id + name;
	}
}
