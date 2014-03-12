package com.soql.metadata;

import java.util.EventListener;

public interface MetadataRepositoryListener extends EventListener {

	public void objectLoaded(MetadataRepositoryEvent event);
	public void objectListLoaded(MetadataRepositoryEvent event);
	public void objectLoading(MetadataRepositoryEvent event);
}
