package com.soql.query;

public class QueryField 
	implements Term{

	private String name;
	
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
		return name;
	}
}
