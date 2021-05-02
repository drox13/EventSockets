package views;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Command;
import models.entity.Concert;

public class PanelTable extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final int COLUMN_WITH_JBUTTON = 2;
	private DefaultTableModel model;
	private final String [] head = {"ID","CONCERT", "SHOW CONCERT"};
	private static final String BTN_BACKGROUND_COLOR = "#1c79c0";
	private JTable table;
	private ActionListener actionListener;

	public PanelTable(ActionListener actionListener) {
		this.actionListener = actionListener;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		model = new DefaultTableModel();
		model.setColumnIdentifiers(head);
		table = new JTable(model);
		table.getTableHeader().setBackground(Color.decode("#1AAFE8"));
		table.setRowHeight(50);
		centerTextinCell();
		table.getColumnModel().getColumn(COLUMN_WITH_JBUTTON).setCellRenderer(new JButtonCellRender());
		table.getColumnModel().getColumn(COLUMN_WITH_JBUTTON).setCellEditor(new JButtonCellEditor());
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
			JButton btnDelete = new JButton("ver Tickets " + concert.getId());
			btnDelete.addActionListener(actionListener);
			btnDelete.setActionCommand(Command.SHOW_CONCERT.toString());
			btnDelete.setBackground(Color.decode(BTN_BACKGROUND_COLOR));
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setName(String.valueOf(concert.getId()));
			model.addRow(new Object[] {concert.getId(),concert.getName(), btnDelete});
		}
	}
}
