package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.entity.Concert;

public class DialogTickets extends JDialog{

	private static final long serialVersionUID = 1L;
	private final int SIZE_DIALOG = 500;
	private final String TITLE = "Boletas";
	private final String SEND = "Vendido";
	private final String AVAILABLE = "Disponible";
	private JPanel panelContenBtn;
	private JButton btnsTickets;
	private int number = Concert.number/2;

	public DialogTickets(JFrame frame) {
		super(frame);
		setTitle(TITLE);
		setSize(SIZE_DIALOG, SIZE_DIALOG);
		setLocationRelativeTo(frame);
		setModal(true);
		setLayout(new BorderLayout());
		panelContenBtn = new JPanel();
		if(number != 0) {
			panelContenBtn.setLayout(new GridLayout(number, number*2));
		}
		panelContenBtn.setBackground(Color.WHITE);
	}

	public void fillDialog(boolean[] booleans) {
		panelContenBtn.removeAll();
		for (int i = 0; i < booleans.length; i++) {
			btnsTickets = new JButton();
			btnsTickets.setName(String.valueOf(i));
			btnsTickets.setFocusable(false);
			btnsTickets.setEnabled(true);
			btnsTickets.setPreferredSize(new Dimension(150, 50));
			panelContenBtn.add(btnsTickets);
			if(booleans[i]) {
				btnsTickets.setText(SEND + " " + btnsTickets.getName());
				btnsTickets.setBackground(Color.decode("#FF554F"));
				btnsTickets.setEnabled(false);
			}else {
				btnsTickets.setText(AVAILABLE + " " + btnsTickets.getName());
				btnsTickets.setBackground(Color.decode("#28B344"));
			}
			add(panelContenBtn, BorderLayout.CENTER);
		}
		revalidate();
		repaint();
	}
}