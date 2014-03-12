package com.soql;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog(JFrame parent){
		super(parent,"About Force.com SOQL Runner",true);

		setLayout(new BorderLayout());
		JTextPane content = new JTextPane();
		content.setContentType("text/html");
		
		content.setText("<html><body>"
				+"<center><h1>SOQL Runner</h1><h2>Version 0.1<h2>"
				+"<p>Author <a href=\"mailto:carlos.descalzi@gmail.com\">Carlos E. Descalzi</a></p>"
				+"</center>"
				+"</body></html>");
		content.setEditable(false);
		add(content,BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		buttons.add(new JButton(new AbstractAction("Close") {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		}));
		add(buttons,BorderLayout.SOUTH);
		
		setSize(300,300);
		UIUtils.center(this);
	}
}
