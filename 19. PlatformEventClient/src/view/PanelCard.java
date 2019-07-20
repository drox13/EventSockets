package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.entity.Concert;

public class PanelCard extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public void refreshPanelCard(ArrayList<Concert> concertList, ActionListener actionListener) {
		removeAll();
		for (Concert concert : concertList) {
			add(new Card(concert, actionListener));
		}
		revalidate();
		repaint();
	}
}