package soql;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.codehaus.jackson.JsonNode;

import soql.client.ApexRestClient;
import soql.client.ApexRestException;

public class SObjectTreeModel implements TreeModel {

	private RequestQueue queue;
	private static final String ROOT = Globals.BUNDLE.getString("sobjectTreeModel.root");
	private List<SObject> objects = new ArrayList<>();
	private List<SObject> allObjects = new ArrayList<>();
	private List<TreeModelListener> listeners = new ArrayList<>();
	
	private void fill(final SObject object) {
		object.loading = true;
		fireNodeChanged(object);
		
		try {
			queue.enqueue(new Request(){
				public void process(ApexRestClient client) throws ApexRestException{
					try {
						doFillObject(client, object);
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
	
	protected void doFillObject(ApexRestClient client, final SObject object) throws Exception{
		JsonNode result = client.doServiceGet("sobjects/"+object.getName()+"/describe");
		JsonNode fields = result.get("fields");
		object.fields = new ArrayList<>();
		for (int i=0;i<fields.size();i++){
			object.fields.add(new Field(fields.get(i)));
		}
		object.loading = false;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				fireNodeChanged(object);
				fireChange(object);
			}
		});
	}

	public SObjectTreeModel(RequestQueue queue){
		this.queue = queue;
		
		try {
			this.queue.enqueue(new Request(){
				public void process(ApexRestClient client) throws ApexRestException{
					final JsonNode rootNode = client.doServiceGet("sobjects");

					
					JsonNode nodes = rootNode.get("sobjects");
					for (int i=0;i<nodes.size();i++){
						SObject object = new SObject(nodes.get(i)); 
						objects.add(object);
						allObjects.add(object);
					}
					
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							fireNodesAdded();
						}
					});
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Object getChild(Object parent, int index) {

		if (parent == ROOT){
			return objects.get(index);
		} else if (parent instanceof SObject){
			return ((SObject)parent).fields.get(index);
		}
		
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent == ROOT){
			return objects.size();
		}
		
		if (parent instanceof SObject){
			SObject obj = (SObject)parent;
			if (obj.fields == null){
				if (!obj.loading){
					fill(obj);
				}
				return 0;
			} else {
				return obj.fields.size();
			}
		}
		
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {

		if (parent == ROOT){
			return objects.indexOf(child);
		} else if (parent instanceof SObject){
			return ((SObject)parent).fields.indexOf(child);
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

	private void fireChange(SObject object) {
		int[] indices = new int[object.fields.size()];
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
	
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	public void setFilterCustomObjects(boolean selected) {
		if (selected){
			objects.clear();
			for (SObject obj:allObjects){
				if (obj.isCustom()){
					objects.add(obj);
				}
			}
		} else {
			objects.clear();
			objects.addAll(allObjects);
		}
		fireNodesAdded();
		
	}

}
