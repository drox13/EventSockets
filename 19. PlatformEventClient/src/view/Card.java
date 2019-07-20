package view;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.entity.Concert;

public class Card extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String TITLE_CARD = "Concierto";
	private static final String TXT_BTN = "Ver Voletas";
	
	public Card(Concert concert, ActionListener actionListener) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel lbTitleCard = new JLabel(TITLE_CARD);
		add(lbTitleCard);
		JLabel lbNameConcert = new JLabel(concert.getId() + "" + concert.getName());
		add(lbNameConcert);
		JButton btnView = new JButton(TXT_BTN);
		btnView.setActionCommand(Command.VIEW_CONCERT.toString());
		btnView.addActionListener(actionListener);
		btnView.setName(String.valueOf(concert.getId()));
		add(btnView);
	}
}