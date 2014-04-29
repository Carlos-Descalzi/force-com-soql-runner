package com.soql.query;


public abstract class FieldTerm implements Term{

	private String alias;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


}
