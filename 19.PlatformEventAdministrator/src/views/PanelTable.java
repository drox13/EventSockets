package views;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.entity.Concert;

public class PanelTable extends JPanel{

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private final String [] head = {"ID","CONCERT"};
	
	public PanelTable() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		model = new DefaultTableModel();
		model.setColumnIdentifiers(head);
		table = new JTable(model);
		table.getTableHeader().setBackground(Color.decode("#1AAFE8"));
		table.setRowHeight(50);
		centerTextinCell();
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		add(scroll);
	}
	
	private void centerTextinCell() {
		DefaultTableCellRenderer align = new DefaultTableCellRenderer();
		align.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(align);			
		}
	}
	
	public void fillTable(ArrayList<Concert>concerts) {
		model.setRowCount(0);
		for (Concert concert : concerts) {
			model.addRow(concert.toVectorTable());
		}
	}
}
