package com.soql;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import org.codehaus.jackson.JsonNode;

import com.soql.query.QueryObject;

@SuppressWarnings("serial")
public class ResultsView 
	extends JPanel
	implements ClipboardOwner{

	private JTable table = new JTable();
	private ResultTableModel model;

	public ResultsView(JsonNode records, QueryObject fields){
		setLayout(new BorderLayout());
		model = new ResultTableModel(records,fields);
		table.setDefaultRenderer(SubqueryResultTableModel.class, new SubQueryResultsRenderer());
		table.setDefaultEditor(SubqueryResultTableModel.class, new SubQueryResultsRenderer());
		table.setModel(model);
		table.setCellSelectionEnabled(true);
		table.setRowSorter(new TableRowSorter<>(model));
		add(
			new JScrollPane(
				table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
	
	public void writeContents(Writer writer) throws IOException{
		CsvWriter csvWriter = new CsvWriter(writer);
		csvWriter.write(model);
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

}
