package soql;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

import soql.LoginDialog.Response;
import soql.client.ApexRestClient;
import soql.editor.SOQLEditor;

@SuppressWarnings("serial")
public class MainWindow 
	extends JFrame{

	
	private SOQLEditor queryEditor = new SOQLEditor();
	private JTabbedPane results = new JTabbedPane();

	private DefaultListModel<String> queries = new DefaultListModel<>();
	private JList<String> queriesView = new JList<String>(queries);
	private ApexRestClient client; 
	private String workingDir;

	private Action runAction = new AbstractAction("Run"){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			runQuery();
		}
	};
	private Action loginAction = new AbstractAction("Login"){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			login();
		}
	};
	
	public static void main(String[] args){
		new MainWindow().setVisible(true);;
	}
	
	public MainWindow(){
		buildUI();
		
		workingDir = System.getProperty("user.home")+File.separator+".query-runner";
		new File(workingDir).mkdirs();
		loadQueries();
		
		setSize(getToolkit().getScreenSize());
		setState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateStatus();
	}
	
	private void loadQueries() {
		Properties p = new Properties();
		File file = new File(workingDir,"queries");
		if (file.exists()){
			try (InputStream in = new FileInputStream(file)){
				p.load(in);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			for (int i=0;;i++){
				String query = p.getProperty("q"+i);
				if (query == null){
					break;
				}
				queries.addElement(query);
			}
		}
	}

	private void buildUI(){
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JSplitPane top = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		queriesView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2 && queriesView.getSelectedIndex() != -1){
					String query = (String)queriesView.getSelectedValue();
					queryEditor.setText(query);
				}
			}
		});
		
		top.setLeftComponent(new JScrollPane(queriesView,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		top.setRightComponent(new JScrollPane(queryEditor,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		top.setDividerLocation(200);
		split.setTopComponent(top);
		split.setBottomComponent(results);
		split.setDividerLocation(400);
		setLayout(new BorderLayout());
		add(split,BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar();
		add(toolBar,BorderLayout.NORTH);
		toolBar.add(loginAction);
		toolBar.add(runAction);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu queryMenu = new JMenu("Query");
		queryMenu.add(runAction);
		menuBar.add(queryMenu);

	}
	
	private void login(){
		
		final LoginDialog login = new LoginDialog(this);
		
		if (login.run() == Response.OK){
			runLong(new Runnable(){
				public void run(){
					client = new ApexRestClient(
						"na15", "", 
						login.getClientId(), 
						login.getClientSecret(), 
						login.getUserName(), 
						login.getPassword());
					try {
						client.init();
					} catch (Exception e) {
						e.printStackTrace();
						client = null;
					}
					updateStatus();
				}
			});
		}

	}
	
	private void runQuery(){
		runAction.setEnabled(false);
		final String query = queryEditor.getText();
		
		saveQuery(query);

		runLong(new Runnable() {
			
			@Override
			public void run() {
				try {
					JsonNode result = client.doQuery(query.replace("\n", " ").replace("\t", " "));
					
					JsonNode records = result.get("records");
					
					showRecords(query,records);
				}catch (Exception ex){
					ex.printStackTrace();
				}
				runAction.setEnabled(true);
			}
		});
	}
	
	private void runLong(Runnable runnable){
		new WaitDialog(this, runnable);
	}
	
	private void saveQuery(String query){

		for (int i=0;i<queries.getSize();i++){
			if (queries.getElementAt(i).equals(query)){
				return;
			}
		}
		
		queries.addElement(query);
		
		Properties p = new Properties();
		for (int i=0;i<queries.getSize();i++){
			p.setProperty("q"+i, queries.getElementAt(i));
		}
		try (OutputStream out = new FileOutputStream(new File(workingDir,"queries"))){
			p.store(out, "");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void showRecords(String query,final JsonNode records) {
		results.addTab(query, new JScrollPane(new JTable(new ResultTableModel(records)),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		results.setSelectedIndex(results.getTabCount()-1);
	}
	
	private void updateStatus(){
		loginAction.setEnabled(client == null);
		runAction.setEnabled(client != null);
	}
}
