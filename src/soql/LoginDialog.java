package soql;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {

	public enum Response {
		OK,
		CANCEL
	}
	
	private JTextField userName = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JTextField clientId = new JTextField();
	private JPasswordField clientSecret = new JPasswordField();
	
	private Response response;
	
	private Action okAction = new AbstractAction("Ok") {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			accept();
		}
	};
	
	private Action cancelAction = new AbstractAction("Cancel") {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cancel();
		}
	};
	
	public LoginDialog(JFrame parent){
		super(parent,"Login",true);
		JPanel contents = new JPanel();
		contents.setLayout(new GridBagLayout());
		
		userName.setText(Globals.PREFERENCES.get("userName", ""));
		password.setText(Globals.PREFERENCES.get("password", ""));
		clientId.setText(Globals.PREFERENCES.get("clientId", ""));
		clientSecret.setText(Globals.PREFERENCES.get("clientSecret", ""));
		
		GridBagConstraints cts = new GridBagConstraints(0,0,1,1,0,1,GridBagConstraints.WEST,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0);
		contents.add(new JLabel("User"),cts);
		cts.gridx = 1;
		cts.weightx = 1;
		contents.add(userName,cts);
		
		cts.gridy = 1;
		cts.gridx = 0;
		cts.weightx = 0;
		contents.add(new JLabel("Password"),cts);
		cts.gridx = 1;
		cts.weightx = 1;
		contents.add(password,cts);
		
		cts.gridy = 2;
		cts.gridx = 0;
		cts.weightx = 0;
		contents.add(new JLabel("Client ID"),cts);
		cts.gridx = 1;
		cts.weightx = 1;
		contents.add(clientId,cts);
		
		cts.gridy = 3;
		cts.gridx = 0;
		cts.weightx = 0;
		contents.add(new JLabel("Client Secret"),cts);
		cts.gridx = 1;
		cts.weightx = 1;
		contents.add(clientSecret,cts);
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttons.add(new JButton(okAction));
		buttons.add(new JButton(cancelAction));
		
		setLayout(new BorderLayout());
		
		add(contents,BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
		
		setSize(300,200);
		UIUtils.center(this);
	}
	
	private void accept(){
		
		Globals.PREFERENCES.put("userName", userName.getText());
		Globals.PREFERENCES.put("password", new String(password.getPassword()));
		Globals.PREFERENCES.put("clientId", clientId.getText());
		Globals.PREFERENCES.put("clientSecret", new String(clientSecret.getPassword()));
		
		response = Response.OK;
		setVisible(false);
	}
	
	private void cancel(){
		response = Response.CANCEL;
		setVisible(false);
	}
	
	public Response run(){
		response = null;
		setVisible(true);
		
		return response;
	}
	
	public String getUserName(){
		return userName.getText();
	}
	
	public String getPassword(){
		return new String(password.getPassword());
	}
	
	public String getClientId(){
		return clientId.getText();
	}
	
	public String getClientSecret(){
		return new String(clientSecret.getPassword());
	}
}
