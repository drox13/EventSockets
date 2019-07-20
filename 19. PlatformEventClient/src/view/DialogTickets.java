package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogTickets extends JDialog{

	private static final long serialVersionUID = 1L;
	private final int SIZE_DIALOG= 200;
	
	public DialogTickets(JFrame frame) {
		super(frame);
		setLocationRelativeTo(frame);
		setLayout(new GridLayout(2, 2));
		setSize(SIZE_DIALOG, SIZE_DIALOG);
		setModal(true);
	}
	
	public void fillDialog(Boolean[] booleans) {
		for (int i = 0; i < booleans.length; i++) {
			JButton btn = new JButton();
			if(booleans[i]) {
				btn.setText("Disponible");
				btn.setBackground(Color.GREEN);
			}else {
				btn.setText("Vendido");
				btn.setBackground(Color.RED);
				btn.setEnabled(false);
			}
		}
	}
}
