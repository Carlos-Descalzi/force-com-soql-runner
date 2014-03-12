package com.soql.metadata.file;

import java.util.ArrayList;
import java.util.List;

import com.soql.metadata.Field;
import com.soql.metadata.SObject;

public class DefaultSObjectImpl implements SObject {

	private String name;
	private boolean custom;
	private List<Field> fields = new ArrayList<Field>();
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isCustom() {
		return custom;
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	@Override
	public boolean isLoaded() {
		return fields != null;
	}

	public void update(){
		
	}
	
	public boolean isLoading(){
		return false;
	}
}
