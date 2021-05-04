package observer;

import java.util.ArrayList;

import models.entity.Concert;

public interface IObserver {
	void fillDialogTickets(boolean[] booleans);

	void updateTable(ArrayList<Concert> concertList);
}