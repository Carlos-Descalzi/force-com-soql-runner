package com.soql;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CloseAllButThisTabAction extends AbstractAction{
	private JTabbedPane tabbedPane;
	private Tab tab;
	public CloseAllButThisTabAction(JTabbedPane tabbedPane,Tab tab){
		super("Close all but this tab");
		this.tab = tab;
		this.tabbedPane = tabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		for (int i=tabbedPane.getTabCount()-1;i>=0;i--){
			if (tabbedPane.getTabComponentAt(i) != tab){
				tabbedPane.remove(i);
			}
		}
		
	}
}