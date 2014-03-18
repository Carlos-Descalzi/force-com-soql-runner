package com.soql;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CloseTabAction extends AbstractAction{
	private JTabbedPane tabbedPane;
	private Tab tab;
	public CloseTabAction(JTabbedPane tabbedPane,Tab tab){
		super("Close Tab");
		this.tab = tab;
		this.tabbedPane = tabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		tabbedPane.remove(tabbedPane.indexOfTabComponent(tab));
		
	}
}