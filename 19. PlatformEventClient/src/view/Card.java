package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.entity.Concert;

public class Card extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String TITLE_CARD = "Concierto";
	private static final String TXT_BTN = "Ver Boletas";
	private Font font = new Font("Arial", Font.PLAIN, 14);	
	
	public Card(Concert concert, ActionListener actionListener) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#99E8FF"));
		
		JLabel lbTitleCard = new JLabel(TITLE_CARD, SwingConstants.CENTER);
		lbTitleCard.setFont(font);
		add(lbTitleCard);

		JLabel lbNameConcert = new JLabel(concert.getName(), SwingConstants.CENTER);
		lbNameConcert.setFont(font);
		add(lbNameConcert);

		JLabel lbDate = new JLabel(concert.getDateFormat(), SwingConstants.CENTER);
		lbDate.setFont(font);
		add(lbDate);

		JButton btnView = new JButton(TXT_BTN);
		btnView.setActionCommand(Command.VIEW_TICKETS.toString());
		btnView.addActionListener(actionListener);
		btnView.setName(String.valueOf(concert.getId()));
		btnView.setFocusable(false);
		btnView.setBackground(Color.decode("#FF336E"));
		btnView.setForeground(Color.WHITE);
		add(btnView);
	}
}