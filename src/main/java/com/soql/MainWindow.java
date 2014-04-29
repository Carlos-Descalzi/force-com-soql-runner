package com.soql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;

import com.soql.LoginDialog.Response;
import com.soql.SObjectTree.Selection;
import com.soql.client.ApexRestClient;
import com.soql.client.ApexRestException;
import com.soql.editor.SOQLEditor;
import com.soql.metadata.Field;
import com.soql.parser.SOQLLexer;
import com.soql.parser.SOQLParser;
import com.soql.query.Query;

@SuppressWarnings("serial")
public class MainWindow 
	extends JFrame
	implements RequestQueueExceptionLogger{

	private static final Log LOG = LogFactory.getLog(MainWindow.class);
	
	private JTextPane logs = new JTextPane();
	private JTabbedPane queryTabs = new JTabbedPane();
	private JTabbedPane results = new JTabbedPane();
	private QueriesModel queries = new QueriesModel();
	private JList<String> queriesView = new JList<String>(queries);
	private SObjectTree objectsInfo = new SObjectTree();
	private RequestQueue queue;
	private String workingDir;
	private JFileChooser chooser;

	private PrintWriter logsWriter = new PrintWriter(new JTextPaneWriter(logs));
	
	private Action newAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.new")){
		@Override
		public void actionPerformed(ActionEvent event) {
			createEditor();
		}
	};
	
	private Action openAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.open")){
		@Override
		public void actionPerformed(ActionEvent event) {
			doOpen();
		}
	};
	private Action saveAction = new AbstractAction(Globals.BUNDLE.getString("mainWindow.action.save")){
		@Override
		public void actionPerformed(ActionEvent event) {
			doSave();
		}
	};
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
	
	private void doOpen() {
		JFileChooser chooser = getChooser();
		
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File selectedFile = chooser.getSelectedFile();
			
			try {
				String query = FileUtils.readFileToString(selectedFile);
				
				createEditor().setText(query);
			}catch (IOException ex){
				showErrorMessage("There was an error opening the file "+selectedFile.getName(),ex);
			}
		}
	}

	protected void doSave() {
		
		File file = getFileForSave();
		
		if (file != null){
			try {
				FileUtils.write(file, getCurrentEditor().getText());
			} catch (IOException e) {
				writeLog(e);
			}
		}
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
	private void showErrorMessage(String message, IOException ex) {
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.ERROR_MESSAGE);
		ex.printStackTrace(logsWriter);
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
					addText(query);
				}
			}
		});
		queriesView.setCellRenderer(new QueryDescriptionListCellRenderer());
		objectsInfo.addSObjectTreeListener(new SObjectTreeListener() {
			
			@Override
			public void queryCreationRequested(SObjectTreeEvent event) {
				createQuery(event.getSelection());
				
			}
		});
		
		leftPanel.addTab("Queries", new JScrollPane(queriesView,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		leftPanel.addTab("Objects", objectsInfo);
		topSplit.setLeftComponent(leftPanel);
		topSplit.setRightComponent(queryTabs);
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
		toolBar.add(openAction);
		toolBar.add(saveAction);
		toolBar.add(loginAction);
		toolBar.add(runAction);
		toolBar.add(exportAction);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu(Globals.BUNDLE.getString("mainWindow.menu.file"));
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
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

		createEditor();
	}
	
	private SOQLEditor createEditor(){
		SOQLEditor queryEditor = new SOQLEditor();
		queryEditor.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent event) {
				updateStatus();
			}
			@Override
			public void insertUpdate(DocumentEvent event) {
				updateStatus();
			}
			@Override
			public void changedUpdate(DocumentEvent event) {
				updateStatus();
			}
		});

		addTab(queryTabs,"New Tab",new JScrollPane(queryEditor,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		return queryEditor;
	}
	
	private void addTab(JTabbedPane tabbedPane,String label, JComponent content){
		tabbedPane.add(content);
		final Tab tab = new Tab(tabbedPane,label);
		
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, tab);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
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
		
		
		addText(b.toString());
		
	}
	
	private void addText(String text){
		SOQLEditor editor = getCurrentEditor();
		
		int start = editor.getCaretPosition();
		
		String currentText = editor.getText();
		if (currentText.trim().equals("")) {
			editor.setText(text);
		} else {
			editor.setText(StringUtils.join(new String[]{currentText,text},"\n\n"));
		}
		editor.setSelectionStart(start);
		editor.setSelectionEnd(editor.getDocument().getLength()-1);
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
						writeLog(e);
						client = null;
					}
					
					
					updateStatus();
				}

			});
		}

	}
	
	private void export(){
		ResultsView results = getSelectedResultsModel();
		File file = getFileForSave();
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
	private SOQLEditor getCurrentEditor(){
		int index = queryTabs.getSelectedIndex();
		return (SOQLEditor)((JScrollPane)queryTabs.getComponentAt(index)).getViewport().getView();
	}


	private File getFileForSave() {
		JFileChooser chooser = getChooser();
		while (true){
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				File selectedFile = chooser.getSelectedFile();
				if (selectedFile.exists()){
					
					int option = JOptionPane.showConfirmDialog(
						this, 
						Globals.BUNDLE.getString("mainWindow.file.save.fileExists"),
						Globals.BUNDLE.getString("mainWindow.file.save.warning"),
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
		final String query = getQuery();
		
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
					writeLog(ex);
				} finally {
					runAction.setEnabled(true);
				}
			}
		});
	}

	private String getQuery() {
		SOQLEditor editor = getCurrentEditor();
		String query = editor.getSelectedText();
		if (StringUtils.isBlank(query)){
			query = editor.getText();
		}
		return query;
	}
	
	@Override
	public void apexException(ApexRestException exception) {
		writeLog(exception);
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
		
		Query fields = parseQuery(query);
		results.add(new ResultsView(records,fields));
		results.setTabComponentAt(results.getTabCount()-1, new Tab(results, query));
		results.setSelectedIndex(results.getTabCount()-1);
		updateStatus();
	}

	private Query parseQuery(String query) throws IOException {
		SOQLParser parser = new SOQLParser(new BufferedTokenStream(new SOQLLexer(new ANTLRInputStream(new StringReader(query)))));
		QueryEvaluator evaluator = new QueryEvaluator();
		parser.addParseListener(evaluator);
		parser.query();
		Query fields = evaluator.getRoot();
		return fields;
	}
	
	private void updateStatus(){
		loginAction.setEnabled(queue == null);
		runAction.setEnabled(queue != null);
		exportAction.setEnabled(results.getSelectedIndex() != -1);
		saveAction.setEnabled(getCurrentEditor().getDocument().getLength() > 0);
	}
	
	public void exit(){
		System.exit(0);
	}

	
	private void showAboutDialog() {
		new AboutDialog(this).setVisible(true);
	}
	
	private void writeLog(Throwable e){
		if (e instanceof ApexRestException && e.getCause() != null){
			e = e.getCause();
		}
		logs.setText(logs.getText()+e.getClass().getName()+":"+e.getMessage()+"\n");
		LOG.error(e,e);
	}
}
