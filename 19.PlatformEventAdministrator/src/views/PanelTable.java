package views;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.entity.Concert;

public class PanelTable extends JPanel{

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private final String [] head = {"ID","CONCERT"};
	
	public PanelTable() {
		model = new DefaultTableModel();
		model.setColumnIdentifiers(head);
		table = new JTable(model);
		add(new JScrollPane(table));
	}
	
	public void fillTable(ArrayList<Concert>concerts) {
		model.setRowCount(0);
		for (Concert concert : concerts) {
			model.addRow(concert.toVectorTable());
		}
	}
}
