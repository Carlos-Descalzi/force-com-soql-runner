package soql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.codehaus.jackson.JsonNode;

import soql.client.ApexRestClient;

public class SObjectTreeModel implements TreeModel {

	private ApexRestClient client;
	private static final String ROOT = Globals.BUNDLE.getString("sobjectTreeModel.root");
	private List<SObject> objects = new ArrayList<>();
	private List<SObject> allObjects = new ArrayList<>();
	private List<TreeModelListener> listeners = new ArrayList<>();
	
	public class Field {
		private JsonNode node;
		
		public Field(JsonNode node){
			this.node = node;
		}
		
		public String getName(){
			return node.get("name").getTextValue();
		}
		public String toString(){
			return getName() + " ("+node.get("type").getTextValue()+")";
		}
	}
	
	public class SObject{
		private JsonNode node;
		private List<Field> fields = null;
		private boolean loading = false;
		public SObject(JsonNode node){
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
	}
	
	@SuppressWarnings("rawtypes")
	private void fill(final SObject object){
		object.loading = true;
		new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				try {
					doFillObject(object);
				}catch(Exception e){
					e.printStackTrace();
					throw e;
				}
				return null;
			}
			
		}.execute();
	}
	
	protected void doFillObject(final SObject object) throws Exception{
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
				fireChange(object);
			}
		});
	}

	public SObjectTreeModel(ApexRestClient client,JsonNode rootNode){
		this.client = client;
		JsonNode nodes = rootNode.get("sobjects");
		for (int i=0;i<nodes.size();i++){
			SObject object = new SObject(nodes.get(i)); 
			objects.add(object);
			allObjects.add(object);
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
