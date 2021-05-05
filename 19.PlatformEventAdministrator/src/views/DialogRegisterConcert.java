package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import controller.Command;
import models.dao.Administrator;
import models.entity.Concert;

public class DialogRegisterConcert extends JDialog{

	private static final long serialVersionUID = 1L;
	private BackTextJTextField jtxtConcert;
	private BackTextJTextField jtxtNumberTickets;
	private JDateChooser calendar;
	
	public DialogRegisterConcert(JFrame frame ,ActionListener actionListener) {
		setSize(300, 300);
		setLocationRelativeTo(frame);
		setLayout(new GridLayout(4, 1));
		jtxtConcert = new BackTextJTextField();
		jtxtConcert.setBackText("Nombre del concierto");
		add(jtxtConcert);
		
		jtxtNumberTickets = new BackTextJTextField();
		jtxtNumberTickets.setBackText("Numero de boletas");
		jtxtNumberTickets.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isDigit(car)){
				}else{
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		add(jtxtNumberTickets);
		
		calendar = new JDateChooser();
		add(calendar);
		
		JButton btnFilter = new JButton("REGISTRAR");
		btnFilter.setActionCommand(Command.CREATE_CONCERT.toString());
		btnFilter.addActionListener(actionListener);
		btnFilter.setBackground(Color.decode("#00bce4"));
		btnFilter.setForeground(Color.WHITE);
		add(btnFilter);
	}
	
	public Concert getConcert(){
		return Administrator.creatConcert(jtxtConcert.getText(), 
				Integer.parseInt(jtxtNumberTickets.getText()), calendar.getCalendar());
	}

	public void clearRegister() {
		jtxtConcert.setText("");
		jtxtNumberTickets.setText("");
	}
}