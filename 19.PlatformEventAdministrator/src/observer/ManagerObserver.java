package observer;

public class ManagerObserver {
	private IObserver observerWindow;
	
	public ManagerObserver(IObserver observerWindow) {
		this.observerWindow = observerWindow;
	}
	
	public void fillDialogTickets(boolean [] booleans){
		observerWindow.fillDialogTickets(booleans);
	}
}