package models.entity;

public class Concert {
	private int id;
	private static int count;
	private String name;
	private Ticket [] tickets;
	private int numberTickets;
	
	
	public Concert(String name) {
		this.name = name;
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
