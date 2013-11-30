package soql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

public class ResultTableModel extends AbstractTableModel {

	private List<List<String>> rows = new ArrayList<>();

	private List<String> fields;
	public ResultTableModel(JsonNode result, List<String> fields){

		this.fields = fields;
		
		for (int i=0;i<result.size();i++){
			JsonNode row = result.get(i);
			List<String> rowData = evaluateRow(row,fields);
			rows.add(rowData);
			
		}
	}
	private List<String> evaluateRow(JsonNode row, List<String> fields) {
		List<String> result = new ArrayList<String>();
		for (String field:fields){
			
			String[] tokens = StringUtils.split(field,".");
			
			JsonNode val = row;
			
			for (String token:tokens){
				val = val.get(token);
			}
			
			result.add(val != null ? val.asText() : "(null)");
		}
		return result;
	}
	@Override
	public int getColumnCount() {
		return fields.size();
	}

	@Override
	public String getColumnName(int column) {
		return fields.get(column);
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return rows.get(row).get(column);
	}

	private Object formatObject(JsonNode col) {
		if (col.isTextual()){
			return col.getTextValue();
		}
		
		List<String> items = new ArrayList<String>();
		
		for (Iterator<String> i = col.getFieldNames();i.hasNext();){
			String fieldName = i.next();
			
			if (fieldName.equals("attributes")){
				continue;
			}
			
			JsonNode value = col.get(fieldName);
			
			items.add(fieldName+":"+formatObject(value));
		}
		
		return "["+StringUtils.join(items,",")+"]";
	}
}
