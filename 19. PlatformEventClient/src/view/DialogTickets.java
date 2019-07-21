package view;

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
	private JButton btn;

	public DialogTickets(JFrame frame) {
		super(frame);
		setTitle(TITLE);
		setLocationRelativeTo(frame);
		setSize(SIZE_DIALOG, SIZE_DIALOG);
		setModal(true);
		panelContenBtn = new JPanel();
		panelContenBtn.setLayout(new GridLayout(2, 2));
	}

	public void fillDialog(boolean[] booleans, ActionListener actionListener) {
		panelContenBtn.removeAll();
		for (int i = 0; i < booleans.length; i++) {
			btn = new JButton();
			btn.addActionListener(actionListener);
			btn.setActionCommand(Command.SEND_ID_CONCERT_AND_TICKET.toString());
			btn.setName(String.valueOf(i));
			panelContenBtn.add(btn);
			if(booleans[i]) {
				btn.setText(SEND + " " + btn.getName());
				btn.setBackground(Color.RED);
				btn.setEnabled(false);
			}else {
				btn.setText(AVAILABLE + " " + btn.getName());
				btn.setBackground(Color.GREEN);
			}
			add(panelContenBtn);
		}
		revalidate();
		repaint();
	}

}