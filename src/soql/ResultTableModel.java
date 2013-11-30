package soql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

@SuppressWarnings("serial")
public class ResultTableModel extends AbstractTableModel {

	private List<List<Object>> rows = new ArrayList<>();

	private List<String> fields;
	public ResultTableModel(JsonNode result, List<String> fields){

		this.fields = fields;
		
		List<String[]> fieldTokens = splitFieldTokens(fields);
		
		for (int i=0;i<result.size();i++){
			JsonNode row = result.get(i);
			rows.add(evaluateRow(row,fieldTokens));
		}
	}
	private List<String[]> splitFieldTokens(List<String> fields) {
		List<String[]> fieldTokens = new ArrayList<>();
		for (String field:fields){
			fieldTokens.add(StringUtils.split(field,"."));
		}
		return fieldTokens;
	}
	private List<Object> evaluateRow(JsonNode row, List<String[]> fieldTokens) {
		List<Object> result = new ArrayList<Object>();
		for (String[] field:fieldTokens){
			
			JsonNode val = row;
			
			for (String token:field){
				val = val.get(token);
			}
			
			result.add(getValue(val));
		}
		return result;
	}
	private Object getValue(JsonNode val) {
		if (val != null){
			if (val.isBoolean()) return val.asBoolean();
			if (val.isTextual()) return val.asText();
			if (val.isInt()) return val.asInt();
			if (val.isDouble()) return val.asDouble();
			if (val.isLong()) return val.asLong();
		}
		return null;
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

}
