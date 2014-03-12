package com.soql;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Tab extends JPanel {

	private JTabbedPane tabs;
	public Tab(JTabbedPane tabs,String label){
		this.tabs = tabs;
		setOpaque(false);
		JPanel tabLabel = new JPanel(new BorderLayout());
		tabLabel.add(new JLabel(label),BorderLayout.CENTER);
		JButton button =new JButton(new AbstractAction("X") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = Tab.this.tabs.getSelectedIndex();
				Tab.this.tabs.removeTabAt(index);
			}
		}); 
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setMargin(new Insets(0,5,0,0));
		tabLabel.add(button,BorderLayout.EAST);
		setLayout(new GridBagLayout());
		add(tabLabel,new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
	}
}
