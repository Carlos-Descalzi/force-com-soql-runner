package com.soql.metadata.rest;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import com.soql.Request;
import com.soql.client.ApexRestClient;
import com.soql.client.ApexRestException;
import com.soql.metadata.Field;
import com.soql.metadata.SObject;

public class SObjectJSONImpl
	implements SObject{

	private JsonNode node;
	private List<Field> fields = null;
	private boolean loading = false;
	private RemoteMetadataRepository repository;
	
	public SObjectJSONImpl(RemoteMetadataRepository repository, JsonNode node){
		this.repository = repository;
		this.node = node;
	}
	public String getName(){
		return node.get("name").getTextValue();
	}
	public String toString(){
		return getName();
	}
	public boolean isCustom() {
		return node.get("custom").asBoolean();
	}
	public boolean isLoading(){
		return loading;
	}
	
	public List<Field> getFields(){
		return fields;
	}
	
	public boolean isLoaded(){
		return fields != null;
	}
	
	public void update(){
		loading = true;
		
		repository.fireObjectLoading(this);
		try {
			repository.getQueue().enqueue(new Request(){
				public void process(ApexRestClient client) throws ApexRestException{
					try {
						doFillObject(client);
					} catch (ApexRestException ex){
						throw ex;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (InterruptedException e1) {
		}
	}
	
	protected void doFillObject(ApexRestClient client) throws Exception{
		JsonNode result = client.doServiceGet("sobjects/"+this.getName()+"/describe");
		JsonNode fields = result.get("fields");
		this.fields = new ArrayList<>();
		for (int i=0;i<fields.size();i++){
			this.fields.add(new FieldJSONImpl(fields.get(i)));
		}
		this.loading = false;
		repository.fireObjectLoaded(this);
	}

}