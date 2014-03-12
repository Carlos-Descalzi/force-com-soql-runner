package com.soql.metadata;

import java.util.EventObject;

@SuppressWarnings("serial")
public class MetadataRepositoryEvent extends EventObject {

	private SObject sobject;
	
	public MetadataRepositoryEvent(Object source) {
		this(source,null);
	}
	
	public MetadataRepositoryEvent(Object source,SObject sobject) {
		super(source);
		this.sobject = sobject;
	}
	public SObject getSObject() {
		return sobject;
	}

	
}
