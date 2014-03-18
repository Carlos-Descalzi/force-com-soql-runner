package com.soql;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class SubQueryResultsRenderer extends JPanel implements
		TableCellRenderer,
		TableCellEditor, ActionListener{

	private JTable table = new JTable();
	private JScrollPane scroll;
	private JButton resume = new JButton();
	private int row;
	private JTable parentTable;

	public SubQueryResultsRenderer(){
		setLayout(new BorderLayout());
		resume.addActionListener(this);
		resume.setMargin(new Insets(0,0,0,0));
		resume.setBorderPainted(false);
		resume.setFocusPainted(false);
		resume.setContentAreaFilled(false);
		add(resume,BorderLayout.NORTH);
		scroll = new JScrollPane(this.table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	private SubqueryResultTableModel value;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		if (value != null){
			SubqueryResultTableModel model = (SubqueryResultTableModel)value;

			rebuildView(model); 
		}
		return this;
	}

	private void rebuildView(SubqueryResultTableModel model) {
		remove(scroll);
		resume.setText(model.getRowCount()+" rows");
		if (model.isExpanded()){
			this.table.setCellSelectionEnabled(true);
			this.table.setModel(model);
			this.table.setRowSorter(new TableRowSorter<>(model));
			add(scroll,BorderLayout.CENTER);
			validate();
		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object arg1,
			boolean arg2, int row, int column) {
		value = (SubqueryResultTableModel)arg1;
		this.row = row;
		this.parentTable = table;
		return getTableCellRendererComponent(table, arg1, arg2, false, row, column);
	}

	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
	}

	@Override
	public void cancelCellEditing() {
	}

	@Override
	public Object getCellEditorValue() {
		return value;
	}

	@Override
	public boolean isCellEditable(EventObject event) {
		return event instanceof MouseEvent && ((MouseEvent)event).getButton() == 1;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
	}

	@Override
	public boolean shouldSelectCell(EventObject event) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		value.setExpanded(!value.isExpanded());		
		if (value.isExpanded()){
			parentTable.setRowHeight(row, 200);
		} else {
			parentTable.setRowHeight(row,parentTable.getRowHeight());
		}
		rebuildView(value);
	}

}
