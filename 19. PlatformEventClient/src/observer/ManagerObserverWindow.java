package observer;

import java.util.ArrayList;

import models.entity.Concert;

public class ManagerObserverWindow {
	private IobserverWindow observerWindows;
	
	public ManagerObserverWindow(IobserverWindow observerWindows) {
		this.observerWindows = observerWindows;
	}
	
	public void notifyNewConcert(String message) {
		observerWindows.notify(message);
	}
	
	public void refreshConcertList(ArrayList<Concert> concerts) {
		observerWindows.refreshConsert(concerts);
	}
}