package com.soql;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.soql.metadata.Field;
import com.soql.metadata.MetadataRepository;
import com.soql.metadata.MetadataRepositoryEvent;
import com.soql.metadata.MetadataRepositoryListener;
import com.soql.metadata.SObject;

public class SObjectTreeModel implements TreeModel, MetadataRepositoryListener {

	private MetadataRepository repository;
	private static final String ROOT = Globals.BUNDLE.getString("sobjectTreeModel.root");
	private List<TreeModelListener> listeners = new ArrayList<>();
	
	public SObjectTreeModel(MetadataRepository repository){
		this.repository = repository;
		this.repository.addListener(this);
	}

	@Override
	public Object getChild(Object parent, int index) {

		if (parent == ROOT){
			return repository.getObjects().get(index);
		} else if (parent instanceof SObject){
			return ((SObject)parent).getFields().get(index);
		}
		
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent == ROOT){
			return repository.getObjects().size();
		}
		
		if (parent instanceof SObject){
			SObject obj = (SObject)parent;
			if (!obj.isLoaded()){
				if (!obj.isLoading()){
					obj.update();
				}
				return 0;
			} else {
				return obj.getFields().size();
			}
		}
		
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {

		if (parent == ROOT){
			return repository.getObjects().indexOf(child);
		} else if (parent instanceof SObject){
			return ((SObject)parent).getFields().indexOf(child);
		}
		return 0;
	}

	@Override
	public Object getRoot() {
		return ROOT;
	}

	@Override
	public boolean isLeaf(Object node) {
		return node instanceof Field;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	public void setFilterCustomObjects(boolean selected) {
		repository.setFilterCustomObjects(selected);
//		if (selected){
//			objects.clear();
//			for (SObjectJSONImpl obj:allObjects){
//				if (obj.isCustom()){
//					objects.add(obj);
//				}
//			}
//		} else {
//			objects.clear();
//			objects.addAll(allObjects);
//		}
//		fireNodesAdded();
//		
	}

	@Override
	public void objectLoaded(final MetadataRepositoryEvent event) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				fireChange(event.getSObject());
			}
		});
	}

	@Override
	public void objectListLoaded(MetadataRepositoryEvent event) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				fireNodesAdded();
			}
		});
	}

	@Override
	public void objectLoading(final MetadataRepositoryEvent event) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				fireNodeChanged(event.getSObject());
			}
		});
	}

	private void fireChange(SObject object) {
		int[] indices = new int[object.getFields().size()];
		for (int i=0;i<indices.length;indices[i]=i++);
		TreeModelEvent event = new TreeModelEvent(this,
			new Object[]{ROOT,object},
			indices,
			new Object[0]);
		for (TreeModelListener listener:listeners){
			listener.treeNodesInserted(event);
		}
	}

	private void fireNodesAdded(){
		TreeModelEvent event = new TreeModelEvent(this, new Object[]{ROOT});
		for (TreeModelListener listener:listeners){
			listener.treeStructureChanged(event);
		}
	}
	
	private void fireNodeChanged(SObject node){
		TreeModelEvent event = new TreeModelEvent(this, new Object[]{ROOT,node});
		for (TreeModelListener listener:listeners){
			listener.treeNodesChanged(event);
		}
	}
	
}
