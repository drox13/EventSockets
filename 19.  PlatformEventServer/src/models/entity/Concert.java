package models.entity;

public class Concert {
	private int id;
	private static int count;
	private String name;
	private boolean [] tickets;
	private int numberTickets;
	
	public Concert(String name) {
		this.name = name;
		id =++count;
		numberTickets = 4;
		tickets = new boolean[numberTickets];
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
	
	public void setTickets(boolean[] tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return "concierto: " + id +" "+ name + " " + tickets.toString();
	}
}
