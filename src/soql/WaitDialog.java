package soql;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class WaitDialog 
	extends JDialog{

	private static final long serialVersionUID = 3282518292415462771L;

	@SuppressWarnings("rawtypes")
	public WaitDialog(JFrame parent,final Runnable task){
		super(parent,"",true);
		setSize(200,100);
		Dimension screenSize = getToolkit().getScreenSize();
		setLocation((screenSize.width-200)/2,(screenSize.height-100)/2);
		
		JLabel label = new JLabel("Wait please ...");
		label.setHorizontalAlignment(JLabel.CENTER);
		setLayout(new GridLayout(2, 1));
		add(label);
		
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		add(progress);
		
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
}
