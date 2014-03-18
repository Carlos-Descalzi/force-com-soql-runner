package com.soql.query;

public class QueryField 
	extends FieldTerm{

	private String name;
	
	public QueryField(){
		
	}
	
	public QueryField(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return getAlias() == null ? name : getAlias();
	}
}
