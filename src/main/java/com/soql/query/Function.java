package com.soql.query;

public class Function extends FieldTerm {

	private QueryField queryField;
	private String name;
	
	public QueryField getQueryField() {
		return queryField;
	}

	public void setQueryField(QueryField queryField) {
		this.queryField = queryField;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public String toString(){
		return getAlias() == null ? (name+"("+queryField.toString()+")") : getAlias();
	}
}
