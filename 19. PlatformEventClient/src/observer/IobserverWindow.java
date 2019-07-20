package observer;

import java.util.ArrayList;

import models.entity.Concert;

public interface IobserverWindow {
	void notify(String message);
	void refreshConsert(ArrayList<Concert> concerts);
	void fillDialog(boolean[] booleans);
}
