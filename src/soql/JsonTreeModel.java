package soql;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.codehaus.jackson.JsonNode;

public class JsonTreeModel implements TreeModel {
	
	private JsonNode node;
	
	public JsonTreeModel(JsonNode node){
		this.node = node;
	}
	@Override
	public void addTreeModelListener(TreeModelListener arg0) {}
	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {}
	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {}

	@Override
	public Object getChild(Object parent, int index) {
		return ((JsonNode)parent).get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((JsonNode)parent).size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}

	@Override
	public Object getRoot() {
		return node;
	}

	@Override
	public boolean isLeaf(Object parent) {
		JsonNode node = (JsonNode)parent;
		return node.isTextual();
	}


}
