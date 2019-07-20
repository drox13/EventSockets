package models.entity;

public class Concert {
	private int id;
	private static int count;
	private String name;
	private boolean [] tickets;
	private int numberTickets;
	public Concert(String name) {
		this.name = name;
		this.numberTickets = 4;
		tickets = new boolean[numberTickets];
		id =++count;
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
	
	@Override
	public String toString() {
		return id + name;
	}
}
