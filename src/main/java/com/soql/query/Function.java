package com.soql.query;


public class Function extends FieldTerm {

	private Field field;
	private String name;
	

	public String toString(){
		return getAlias() == null ? (name+"("+field.toString()+")") : getAlias();
	}

	@Override
	public String getName() {
		return name;
	}

	
}
