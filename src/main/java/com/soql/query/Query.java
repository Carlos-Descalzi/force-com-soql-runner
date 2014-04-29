package com.soql.query;

import java.util.ArrayList;
import java.util.List;

public class Query implements Term{

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
	
	public void add(Term term){
		terms.add(term);
	}
	
	public String toString(){
		return name;
	}
}
