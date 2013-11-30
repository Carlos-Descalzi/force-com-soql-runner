package soql;

import java.io.IOException;
import java.io.Writer;

import javax.swing.table.TableModel;

public class CsvWriter {

	private Writer writer;
	public CsvWriter(Writer writer){
		this.writer = writer;
	}
	
	public void write(TableModel model) throws IOException{
		
		for (int i=0;i<model.getColumnCount();i++){
			if (i>0){
				writer.write(",");
			}
			writer.write("\""+model.getColumnName(i)+"\"");
		}
		writer.write("\n");
		
		for (int i=0;i<model.getRowCount();i++){
			for (int j=0;j<model.getColumnCount();j++){
				if (i>0){
					writer.write(",");
				}
				writer.write(String.valueOf(model.getValueAt(i, j)));
				
			}
			writer.write("\n");
		}
	}
}
