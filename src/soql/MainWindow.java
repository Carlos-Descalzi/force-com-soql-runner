package soql;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.codehaus.jackson.JsonNode;

import soql.LoginDialog.Response;
import soql.SObjectTree.Selection;
import soql.client.ApexRestClient;
import soql.client.ApexRestException;
import soql.editor.SOQLEditor;
import soql.parser.SOQLLexer;
import soql.parser.SOQLParser;

@SuppressWarnings("serial")
public class MainWindow 
	extends JFrame
	implements RequestQueueExceptionLogger{

	
	private SOQLEditor queryEditor = new SOQLEditor();
	private JTextPane logs = new JTextPane();
	private JTabbedPane results = new JTabbedPane();

	private QueriesModel queries = new QueriesModel();
	private JList<String> queriesView = new JList<String>(queries);
	private SObjectTree objectsInfo = new SObjectTree();
	private RequestQueue queue;
//	private ApexRestClient client; 
	private String workingDir;
	private JFileChooser chooser;

	private Action runAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.run")){
		@Override
		public void actionPerformed(ActionEvent event) {
			runQuery();
		}
	};
	private Action loginAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.login")){
		@Override
		public void actionPerformed(ActionEvent event) {
			login();
		}
	};
	private Action exportAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.export")){
		@Override
		public void actionPerformed(ActionEvent event) {
			export();
		}
	};
	private Action exitAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.exit")){
		@Override
		public void actionPerformed(ActionEvent event) {
			exit();
		}
	};
	private Action aboutAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.about")){
		@Override
		public void actionPerformed(ActionEvent event) {
			showAboutDialog();
		}

	};
	
	public static void main(String[] args){
		new MainWindow();
	}
	
	public MainWindow(){
		super(Globals.BUNDLE.getString("mainWindow.title"));
		buildUI();
		
		workingDir = System.getProperty("user.home")+File.separator+".query-runner";
		new File(workingDir).mkdirs();
		loadQueries();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateStatus();
	}
	
	private void loadQueries() {
		File file = new File(workingDir,"queries");
		queries.load(file);
	}

	private void buildUI(){
		final JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		final JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		JTabbedPane leftPanel = new JTabbedPane();
		
		queriesView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2 && queriesView.getSelectedIndex() != -1){
					String query = (String)queriesView.getSelectedValue();
					queryEditor.setText(query);
				}
			}
		});
		queriesView.setCellRenderer(new DefaultListCellRenderer(){
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
		});
		objectsInfo.addSObjectTreeListener(new SObjectTreeListener() {
			
			@Override
			public void queryCreationRequested(SObjectTreeEvent event) {
				createQuery(event.getSelection());
				
			}
		});
		leftPanel.addTab("Queries", new JScrollPane(queriesView,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		leftPanel.addTab("Objects", objectsInfo);
		topSplit.setLeftComponent(leftPanel);
		topSplit.setRightComponent(new JScrollPane(queryEditor,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		mainSplit.setTopComponent(topSplit);
		
		final JSplitPane bottomSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		results.setPreferredSize(new Dimension(800,400));
		
		JScrollPane logsPanel = new JScrollPane(logs,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logsPanel.setPreferredSize(new Dimension(800,200));

		bottomSplit.setTopComponent(results);
		bottomSplit.setBottomComponent(logsPanel);
		mainSplit.setBottomComponent(bottomSplit);
		setLayout(new BorderLayout());
		add(mainSplit,BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar();
		add(toolBar,BorderLayout.NORTH);
		toolBar.add(loginAction);
		toolBar.add(runAction);
		toolBar.add(exportAction);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu(Globals.BUNDLE.getString("mainWindow.menu.file"));
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);
		
		JMenu queryMenu = new JMenu(Globals.BUNDLE.getString("mainWindow.menu.query"));
		queryMenu.add(runAction);
		menuBar.add(queryMenu);
	
		JMenu helpMenu = new JMenu(Globals.BUNDLE.getString("mainWindow.menu.help"));
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);

		setSize(getToolkit().getScreenSize());
		addWindowListener(new WindowAdapter() {
			
			public void windowOpened(WindowEvent e){
				Dimension size = getSize();
				mainSplit.setDividerLocation(size.height / 3);
				topSplit.setDividerLocation(size.width / 4);
				bottomSplit.setDividerLocation(size.height  / 3);
			}
		});
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	protected void createQuery(Selection selection) {
		
		StringBuilder b = new StringBuilder();
		b.append("select ");
		for (Iterator<Field> i = selection.getFields().iterator();i.hasNext();){
			b.append(i.next().getName());
			if (i.hasNext()){
				b.append(",");
			}
		}
		b.append(" from ");
		b.append(selection.getObject().getName());
		
		queryEditor.setText(b.toString());
		
	}

	private void login(){
		
		final LoginDialog login = new LoginDialog(this);
		
		if (login.run() == Response.OK){
			runLong(new Runnable(){
				public void run(){
					ApexRestClient client = new ApexRestClient(
						"na15", "", 
						login.getClientId(), 
						login.getClientSecret(), 
						login.getUserName(), 
						login.getPassword());
					try {
						client.init();
						queue = new RequestQueue(client,MainWindow.this);
						objectsInfo.setRequestQueue(queue);
					} catch (Exception e) {
						logs.setText(logs.getText()+"\n"+e.getMessage());
						e.printStackTrace();
						client = null;
					}
					
					
					updateStatus();
				}

			});
		}

	}
	
	private void export(){
		ResultsView results = getSelectedResultsModel();
		File file = getFile();
		try (FileWriter writer = new FileWriter(file)){
			results.writeContents(writer);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private ResultsView getSelectedResultsModel() {
		int index = results.getSelectedIndex();
		return (ResultsView)results.getComponentAt(index);
	}

	private File getFile() {
		JFileChooser chooser = getChooser();
		while (true){
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				File selectedFile = chooser.getSelectedFile();
				if (selectedFile.exists()){
					
					int option = JOptionPane.showConfirmDialog(
						this, 
						Globals.BUNDLE.getString("mainWindow.export.warning"),
						Globals.BUNDLE.getString("mainWindow.export.warning.title"),
						JOptionPane.YES_NO_CANCEL_OPTION);
					
					switch (option){
						case JOptionPane.YES_OPTION:
							return selectedFile;
						case JOptionPane.NO_OPTION:
							break;
						case JOptionPane.CANCEL_OPTION:
							return null;
					}
				} 
				return selectedFile;
			} else {
				return null;
			}
		}
	}
	
	private JFileChooser getChooser(){
		if (chooser == null){
			chooser = new JFileChooser();
		}
		return chooser;
	}

	private void runQuery(){
		runAction.setEnabled(false);
		final String query = queryEditor.getText();
		
		saveQuery(query);

		runLong(new Request() {
			
			@Override
			public void process(ApexRestClient client) throws ApexRestException{
				try {
					JsonNode result = client.doQuery(query.replaceAll("[\n\t ]+", " "));
					
					JsonNode records = result.get("records");
					
					showRecords(query,records);
				}catch (ApexRestException ex){
					throw ex;
				}catch (Exception ex){
					logs.setText(logs.getText()+"\n"+ex.getMessage());
					ex.printStackTrace();
				} finally {
					runAction.setEnabled(true);
				}
			}
		});
	}

	
	
	@Override
	public void apexException(ApexRestException exception) {
		logs.setText(logs.getText()+"\n"+exception.getMessage());
		exception.printStackTrace();
		
	}

	private void runLong(Runnable runnable){
		new WaitDialog(this, runnable);
	}
	private void runLong(Request request){
		new WaitDialog(this, queue, request);
	}
	
	private void saveQuery(String query){
		queries.addQuery(query);
		queries.save(new File(workingDir,"queries"));
	}

	private void showRecords(String query,final JsonNode records) throws Exception{
		
		SOQLParser parser = new SOQLParser(new BufferedTokenStream(new SOQLLexer(new ANTLRInputStream(new StringReader(query)))));
	
		QueryEvaluator evaluator = new QueryEvaluator();
		parser.addParseListener(evaluator);
		parser.query();
		List<String> fields = evaluator.getFields();
		results.add(new ResultsView(records,fields));
		results.setTabComponentAt(results.getTabCount()-1, new Tab(results, query));
		results.setSelectedIndex(results.getTabCount()-1);
		updateStatus();
	}
	
	private void updateStatus(){
		loginAction.setEnabled(queue == null);
		runAction.setEnabled(queue != null);
		exportAction.setEnabled(results.getSelectedIndex() != -1);
	}
	
	public void exit(){
		System.exit(0);
	}

	
	private void showAboutDialog() {
		new AboutDialog(this).setVisible(true);
	}
}
