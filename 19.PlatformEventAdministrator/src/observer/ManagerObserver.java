package observer;

import java.util.ArrayList;

import models.entity.Concert;

public class ManagerObserver {
	private IObserver observerWindow;
	
	public ManagerObserver(IObserver observerWindow) {
		this.observerWindow = observerWindow;
	}
	
	public void fillDialogTickets(boolean [] booleans){
		observerWindow.fillDialogTickets(booleans);
	}

	public void updateTable(ArrayList<Concert> concertList) {
		observerWindow.updateTable(concertList);
	}
}