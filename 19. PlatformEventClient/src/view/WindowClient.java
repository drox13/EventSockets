package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.entity.Concert;
import observer.IobserverWindow;

public class WindowClient extends JFrame implements IobserverWindow{

	private static final long serialVersionUID = 1L;
	private PanelCard panelCard;
	private ActionListener actionListener;
	
	public WindowClient(ActionListener actionListener) {
		this.actionListener = actionListener;
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.actionListener = actionListener;
		panelCard = new PanelCard();
		add(panelCard);
		setVisible(true);
	}
	
	@Override
	public void notify(String message) {
		JOptionPane.showMessageDialog(this, message, "nuevo concierto", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void refreshConsert(ArrayList<Concert> concerts) {
		panelCard.refreshPanelCard(concerts, actionListener);
	}
}