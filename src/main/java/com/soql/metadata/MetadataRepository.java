package com.soql.metadata;

import java.util.ArrayList;
import java.util.List;

public abstract class MetadataRepository {

	protected List<SObject> objects = new ArrayList<SObject>();
	protected List<SObject> allObjects = new ArrayList<SObject>();
	private List<MetadataRepositoryListener> listeners = new ArrayList<MetadataRepositoryListener>();
	

	public void addListener(MetadataRepositoryListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(MetadataRepositoryListener listener){
		listeners.remove(listener);
	}
	
	public void fireObjectLoaded(SObject object){
		MetadataRepositoryEvent event = new MetadataRepositoryEvent(this, object);
		for (MetadataRepositoryListener listener:listeners){
			listener.objectLoaded(event);
		}
	}
	
	public void fireObjectListLoaded(){
		MetadataRepositoryEvent event = new MetadataRepositoryEvent(this);
		for (MetadataRepositoryListener listener:listeners){
			listener.objectListLoaded(event);
		}
	}
	public void fireObjectLoading(SObject object) {
		MetadataRepositoryEvent event = new MetadataRepositoryEvent(this, object);
		for (MetadataRepositoryListener listener:listeners){
			listener.objectLoading(event);
		}
	}

	protected abstract boolean isLoading();
	
	public List<SObject> getObjects(){
		return objects;
	}

	public void setFilterCustomObjects(boolean selected) {
		objects.clear();

		if (selected){
			for (SObject obj:allObjects){
				if (obj.isCustom()){
					objects.add(obj);
				}
			}
		} else {
			objects.addAll(allObjects);
		}
		fireObjectListLoaded();
	}


}
