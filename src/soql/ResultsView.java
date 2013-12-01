package soql;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import org.codehaus.jackson.JsonNode;

@SuppressWarnings("serial")
public class ResultsView 
	extends JPanel{

	private JTable table = new JTable();
	private ResultTableModel model;

	public ResultsView(JsonNode records, List<String> fields){
		setLayout(new BorderLayout());
		model = new ResultTableModel(records,fields);
		table.setModel(model);
		table.setRowSorter(new TableRowSorter<>(model));
		add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
	
	public void writeContents(Writer writer) throws IOException{
		CsvWriter csvWriter = new CsvWriter(writer);
		csvWriter.write(model);
	}
}
