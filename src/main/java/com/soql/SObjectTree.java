package com.soql;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.soql.metadata.Field;
import com.soql.metadata.rest.RemoteMetadataRepository;
import com.soql.metadata.rest.SObjectJSONImpl;

@SuppressWarnings({"serial"})
public class SObjectTree extends JPanel {

	private Action createQueryAction = new AbstractAction(Globals.BUNDLE.getString("sobjectTree.action.createQuery")) {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			createQuery();
		}
	};
	
	private JTree tree = new JTree();
	private List<SObjectTreeListener> listeners = new ArrayList<>();
	private JPopupMenu popup = new JPopupMenu();
	private JCheckBox onlyCustom = new JCheckBox("Show only custom objects");
	public SObjectTree(){
		tree.setModel(new DummyModel(Globals.BUNDLE.getString("sobjectTree.notLoggedIn")));
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				updateActions();
				if (e.getButton() == MouseEvent.BUTTON3){
					popup.show(tree, e.getX(), e.getY());
				}
			}
		});
		popup.add(createQueryAction);
		setLayout(new BorderLayout());
		add(onlyCustom,BorderLayout.NORTH);
		onlyCustom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				filterTree();
			}

		});
		onlyCustom.setEnabled(false);
		add(new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);
	}

	public void setRequestQueue(final RequestQueue queue){
		tree.setModel(new SObjectTreeModel(new RemoteMetadataRepository(queue)));
		onlyCustom.setEnabled(true);
	}

	private void filterTree() {
		TreeModel model = tree.getModel();
		
		if (model instanceof SObjectTreeModel){
			((SObjectTreeModel)model).setFilterCustomObjects(onlyCustom.isSelected());
		}
		
	}
	private class DummyModel extends DefaultTreeModel {
		public DummyModel(String root){
			super(new DefaultMutableTreeNode(root));
		}
	}
	
	private void createQuery(){
		Selection selection = getSelection();
		fireQueryCreationRequested(selection);
	}
	
	private Selection getSelection() {
		TreePath[] paths = tree.getSelectionPaths();
		
		if (paths == null){
			return null;
		}
		List<Field> fields = new ArrayList<Field>();
		Set<Object> parents = new HashSet<Object>();
		for (TreePath path:paths){
			if (path.getLastPathComponent() instanceof Field){
				fields.add((Field)path.getLastPathComponent());
				parents.add(path.getPathComponent(1));
			} else {
				return null;
			}
		}
		
		if (parents.size() > 1){
			return null;
		}

		return new Selection((SObjectJSONImpl)parents.iterator().next(),fields);
	}

	private void updateActions(){
		createQueryAction.setEnabled(getSelection() != null);
	}
	
	public class Selection {
		private SObjectJSONImpl object;
		private List<Field> fields;
		public Selection(SObjectJSONImpl object, List<Field> fields) {
			this.object = object;
			this.fields = fields;
		}
		public SObjectJSONImpl getObject() {
			return object;
		}
		public List<Field> getFields() {
			return fields;
		}
	}
	
	private void fireQueryCreationRequested(Selection selection){
		SObjectTreeEvent event = new SObjectTreeEvent(this, selection);
		for (SObjectTreeListener listener:listeners){
			listener.queryCreationRequested(event);
		}
	}
	
	
	public void addSObjectTreeListener(SObjectTreeListener listener){
		listeners.add(listener);
	}
	
	public void removeSObjectTreeListener(SObjectTreeListener listener){
		listeners.remove(listener);
	}
}
