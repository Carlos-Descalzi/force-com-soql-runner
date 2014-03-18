package com.soql;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

import com.soql.query.QueryObject;
import com.soql.query.Term;

@SuppressWarnings("serial")
public class ResultTableModel extends AbstractTableModel {

	private List<List<Object>> rows = new ArrayList<>();

	private QueryObject fields;
	public ResultTableModel(JsonNode result, QueryObject fields){

		this.fields = fields;
		
		List<String[]> fieldTokens = splitFieldTokens(fields);
		
		for (int i=0;i<result.size();i++){
			JsonNode row = result.get(i);
			rows.add(evaluateRow(row,fieldTokens,fields));
		}
	}
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return getColumnClass(columnIndex) == SubqueryResultTableModel.class;
	}


	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (rows.isEmpty()){
			return String.class;
		}
		Object value = rows.get(0).get(columnIndex);
		return value == null ? String.class : value.getClass();
	}


	private List<String[]> splitFieldTokens(QueryObject fields) {
		List<String[]> fieldTokens = new ArrayList<>();
		for (Term field:fields.getTerms()){
			fieldTokens.add(StringUtils.split(field.getName(),"."));
		}
		return fieldTokens;
	}
	private List<Object> evaluateRow(JsonNode row, List<String[]> fieldTokens, QueryObject fields) {
		List<Object> result = new ArrayList<Object>();
		
		
		for (int i=0;i<fieldTokens.size();i++){
			
			String[] field = fieldTokens.get(i);
			
			Term term = fields.getTerms().get(i);
			
			JsonNode val = row;
			
			for (String token:field){
				val = val.get(token);
				if (val == null){
					break;
				}
			}
			
			result.add(getValue(val,term));
		}
		return result;
	}
	private Object getValue(JsonNode val, Term term) {
		if (val != null){
			if (val.isBoolean()) return val.asBoolean();
			if (val.isTextual()) return val.asText();
			if (val.isInt()) return val.asInt();
			if (val.isDouble()) return val.asDouble();
			if (val.isLong()) return val.asLong();
			if (val.isObject()) return createSubList(val,(QueryObject)term);
		}
		return null;
	}
	private Object createSubList(JsonNode val, QueryObject term) {
		return new SubqueryResultTableModel(val.get("records"), term);
	}
	@Override
	public int getColumnCount() {
		return fields.getTerms().size();
	}

	@Override
	public String getColumnName(int column) {
		return fields.getTerms().get(column).getName();
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
