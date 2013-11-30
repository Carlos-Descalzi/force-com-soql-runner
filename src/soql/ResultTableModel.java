package soql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

public class ResultTableModel extends AbstractTableModel {

	private List<JsonNode> rows = new ArrayList<>();
	private List<String> columns = new ArrayList<>();

	public ResultTableModel(JsonNode result){

		
		for (int i=0;i<result.size();i++){
			JsonNode row = result.get(i);
			rows.add(row);
			if (columns.isEmpty()){
				for (Iterator<String> it = row.getFieldNames();it.hasNext();){
					columns.add(it.next());
				}
			}
		}
		
		columns.remove("attributes");
		
	}
	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		JsonNode record = rows.get(row);
		String colName = columns.get(column);
		JsonNode col = record.get(colName);
		
		if (col.isTextual()){
			return col.getTextValue();
		}
		
		if (col.isObject()){
			return formatObject(col);
		}
		
		return col;
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
