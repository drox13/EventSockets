package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogTickets extends JDialog{

	private static final long serialVersionUID = 1L;
	private final int SIZE_DIALOG = 300;
	private final String TITLE = "Voletas";
	private final String SEND = "Vendido";
	private final String AVAILABLE = "Disponible";
	private JPanel panelContenBtn;
	private JButton btnsTickets;
	private JButton btnConfirm;

	public DialogTickets(JFrame frame) {
		super(frame);
		setTitle(TITLE);
		setLocationRelativeTo(frame);
		setSize(SIZE_DIALOG, SIZE_DIALOG);
		setModal(true);
		setLayout(new BorderLayout());
		panelContenBtn = new JPanel();
		panelContenBtn.setLayout(new GridLayout(2, 2));
	}

	public void fillDialog(boolean[] booleans, ActionListener actionListener) {
		panelContenBtn.removeAll();
		for (int i = 0; i < booleans.length; i++) {
			btnsTickets = new JButton();
			btnsTickets.addActionListener(actionListener);
			btnsTickets.setActionCommand(Command.SEND_ID_CONCERT_AND_TICKET.toString());
			btnsTickets.setName(String.valueOf(i));
			panelContenBtn.add(btnsTickets);
			if(booleans[i]) {
				btnsTickets.setText(SEND + " " + btnsTickets.getName());
				btnsTickets.setBackground(Color.RED);
				btnsTickets.setEnabled(false);
			}else {
				btnsTickets.setText(AVAILABLE + " " + btnsTickets.getName());
				btnsTickets.setBackground(Color.GREEN);
			}
			add(panelContenBtn, BorderLayout.CENTER);
		}
		btnConfirm = new JButton();
		btnConfirm.addActionListener(actionListener);
		btnConfirm.setActionCommand(Command.CONFIRM_PURCHASE.toString());
		btnConfirm.setText("Confirmar compra");
		btnConfirm.setBackground(Color.BLUE);
		add(btnConfirm, BorderLayout.PAGE_END);
		revalidate();
		repaint();
	}

}