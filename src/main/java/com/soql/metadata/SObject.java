package com.soql.metadata;

import java.util.List;

public interface SObject {

	public String getName();

	public boolean isCustom();
	
	public List<Field> getFields();
	
	public boolean isLoading();
	
	public boolean isLoaded();
	
	public void update();
}
