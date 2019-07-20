package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogTickets extends JDialog{

	private static final long serialVersionUID = 1L;
	private final int SIZE_DIALOG = 300;
	private final String TITLE = "Voletas";
	private final String SEND = "Vendido";
	private final String AVAILABLE = "Disponible";
	
	public DialogTickets(JFrame frame) {
		super(frame);
		setTitle(TITLE);
		setLocationRelativeTo(frame);
		setLayout(new GridLayout(2, 2));
		setSize(SIZE_DIALOG, SIZE_DIALOG);
		setModal(true);
	}
	
	public void fillDialog(boolean[] booleans) {
		for (int i = 0; i < booleans.length; i++) {
			JButton btn = new JButton();
			if(booleans[i]) {
				btn.setText(SEND);
				btn.setBackground(Color.RED);
				btn.setEnabled(false);
			}else {
				btn.setText(AVAILABLE);
				btn.setBackground(Color.GREEN);
			}
			add(btn);
		}
		revalidate();
		repaint();
	}
}
