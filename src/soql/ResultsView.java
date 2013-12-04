package soql;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

import sun.swing.StringUIClientPropertyKey;

@SuppressWarnings("serial")
public class ResultsView 
	extends JPanel
	implements ClipboardOwner{

	private JTable table = new JTable();
	private ResultTableModel model;

	public ResultsView(JsonNode records, List<String> fields){
		setLayout(new BorderLayout());
		model = new ResultTableModel(records,fields);
		table.setModel(model);
		table.setCellSelectionEnabled(true);
		table.setRowSorter(new TableRowSorter<>(model));
		add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

	}
	
	public void writeContents(Writer writer) throws IOException{
		CsvWriter csvWriter = new CsvWriter(writer);
		csvWriter.write(model);
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}

}
