package soql;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.codehaus.jackson.JsonNode;

@SuppressWarnings("serial")
public class ResultsView 
	extends JPanel{

	private JTable table = new JTable();
	private ResultTableModel model;
	public ResultsView(JsonNode records){
		setLayout(new BorderLayout());
		model = new ResultTableModel(records);
		table.setModel(model);
		add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
	
	public void writeContents(Writer writer) throws IOException{
		CsvWriter csvWriter = new CsvWriter(writer);
		csvWriter.write(model);
	}
}
