package models.entity;

public class Concert {
	private int id;
	private static int count;
	private String name;
	private Ticket [] tickets;
	private int numberTickets;
	public Concert(String name, int numberTickets) {
		this.name = name;
		this.numberTickets = numberTickets;
		tickets = new Ticket[numberTickets];
		id =++count;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return id + name;
	}
}
