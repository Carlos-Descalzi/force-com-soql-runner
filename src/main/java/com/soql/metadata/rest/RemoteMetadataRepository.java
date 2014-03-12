package com.soql.metadata.rest;

import org.codehaus.jackson.JsonNode;

import com.soql.Request;
import com.soql.RequestQueue;
import com.soql.client.ApexRestClient;
import com.soql.client.ApexRestException;
import com.soql.metadata.MetadataRepository;

public class RemoteMetadataRepository extends MetadataRepository{

	private RequestQueue queue;
	private boolean loading;
	
	public RemoteMetadataRepository(RequestQueue queue){
		this.queue = queue;
		this.loading = true;
		try {
			this.queue.enqueue(new Request(){
				public void process(ApexRestClient client) throws ApexRestException{
					final JsonNode rootNode = client.doServiceGet("sobjects");
					
					JsonNode nodes = rootNode.get("sobjects");
					for (int i=0;i<nodes.size();i++){
						SObjectJSONImpl object = new SObjectJSONImpl(RemoteMetadataRepository.this,nodes.get(i)); 
						allObjects.add(object);
					}
					objects.addAll(allObjects);
					loading = false;
					fireObjectListLoaded();
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected boolean isLoading() {
		return loading;
	}
	public RequestQueue getQueue() {
		return queue;
	}
	
}
