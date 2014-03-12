package com.soql.metadata.rest;

import org.codehaus.jackson.JsonNode;

import com.soql.metadata.Field;

public class FieldJSONImpl implements Field{
	private JsonNode node;
	
	public FieldJSONImpl(JsonNode node){
		this.node = node;
	}
	public String getName(){
		return node.get("name").getTextValue();
	}
	public String toString(){
		return getName() + " ("+node.get("type").getTextValue()+")";
	}
}