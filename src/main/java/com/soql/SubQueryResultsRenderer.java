package com.soql;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.TableCellRenderer;

public class SubQueryResultsRenderer extends JPanel implements
		TableCellRenderer {

	private JTable table;

	private JToggleButton resume = new JToggleButton(new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			toggle(event);
			
		}
	});
	private JPanel detail = new JPanel(new BorderLayout());
	
	public SubQueryResultsRenderer(){
		setLayout(new BorderLayout());
		
	}
	
	protected void toggle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return null;
	}

}
