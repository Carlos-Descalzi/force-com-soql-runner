package com.soql;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

@SuppressWarnings("serial")
public class QueryDescriptionListCellRenderer extends
		DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList<?> list,
			Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		String strValue = (String)value;
		
		if (strValue != null){
			strValue = strValue.replaceAll("[\n\t ]+", " ");
		}
		
		return super.getListCellRendererComponent(list, strValue, index, isSelected,
				cellHasFocus);
	}
}