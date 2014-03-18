package com.soql;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Tab extends JPanel {

	private JPopupMenu tabsMenu = new JPopupMenu();
	
	private JTabbedPane tabs;
	public Tab(JTabbedPane tabs,String label){
		this.tabs = tabs;
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		setOpaque(false);
		setLayout(new BorderLayout());
		add(new JLabel(label),BorderLayout.CENTER);
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
		add(button,BorderLayout.EAST);

		tabsMenu.add(new CloseTabAction(tabs,this));
		tabsMenu.add(new CloseAllButThisTabAction(tabs,this));
	}
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_CLICKED){
			if (e.getButton() == MouseEvent.BUTTON3){
				tabsMenu.show(Tab.this, e.getX(), e.getY());
			} else {
				tabs.setSelectedIndex(tabs.indexOfTabComponent(this));
			}
		} else {
			super.processMouseEvent(e);
		}
	}

	public JTabbedPane getTabbedPane(){
		return tabs;
	}
}
