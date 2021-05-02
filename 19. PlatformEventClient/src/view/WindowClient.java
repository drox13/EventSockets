package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import models.entity.Concert;
import observer.IobserverWindow;

public class WindowClient extends JFrame implements IobserverWindow{

	private static final long serialVersionUID = 1L;
	private PanelCard panelCard;
	private ActionListener actionListener;
	private DialogTickets dialogTickets;
	private PanelQr panelQr;
	
	public WindowClient(ActionListener actionListener) {
		this.actionListener = actionListener;
		setSize(500, 500);
//		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cliente");
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		panelCard = new PanelCard();
		add(panelCard, BorderLayout.CENTER);
		dialogTickets = new DialogTickets(this);
		panelQr = new PanelQr();
		add(new JScrollPane(panelQr), BorderLayout.LINE_START);
		setVisible(true);
	}
	
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public DialogTickets getDialogTickets() {
		return dialogTickets;
	}

	@Override
	public void notify(String message) {
		JOptionPane.showMessageDialog(this, message, "nuevo concierto", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void refreshConsert(ArrayList<Concert> concerts) {
		panelCard.refreshPanelCard(concerts, actionListener);
	}
	
	@Override
	public void fillDialog(boolean[] booleans) {
		dialogTickets.fillDialog(booleans, actionListener);
		dialogTickets.setVisible(true);
	}
	
	public void closeDialog() {
		dialogTickets.setVisible(false);
	}
}