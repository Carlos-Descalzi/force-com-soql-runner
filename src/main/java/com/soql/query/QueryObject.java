package com.soql.query;

import java.util.ArrayList;
import java.util.List;

public class QueryObject implements Term{

	private String name;
	
	private List<Term> terms = new ArrayList<Term>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Term> getTerms(){
		return terms;
	}
	
	public String toString(){
		return name;
	}
}
