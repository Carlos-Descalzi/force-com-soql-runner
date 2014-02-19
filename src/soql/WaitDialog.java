package soql;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import soql.client.ApexRestClient;
import soql.client.ApexRestException;

public class WaitDialog 
	extends JDialog{

	private static final long serialVersionUID = 3282518292415462771L;

	public WaitDialog(JFrame parent){
		super(parent,"",true);
		setSize(200,100);
		UIUtils.center(this);
		
		JLabel label = new JLabel("Wait please ...");
		label.setHorizontalAlignment(JLabel.CENTER);
		setLayout(new GridLayout(2, 1));
		add(label);
		
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		add(progress);
		
	}
	@SuppressWarnings("rawtypes")
	public WaitDialog(JFrame parent,final Runnable task){
		this(parent);
		
		SwingWorker worker = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				task.run();
				return null;
			}
			@Override
			protected void done() {
				WaitDialog.this.setVisible(false);
			}
		};
		worker.execute();
		setVisible(!worker.isDone());
	}
	public WaitDialog(MainWindow parent, RequestQueue queue, final Request request) {
		this(parent);
		Request requestWrapper = new Request(){
			public void process(ApexRestClient client) throws ApexRestException{
				try {
					request.process(client);
				} finally {
					WaitDialog.this.setVisible(false);
				}
			}
		};
		try {
			queue.enqueue(requestWrapper);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setVisible(!queue.isDone(requestWrapper));
	}
}
