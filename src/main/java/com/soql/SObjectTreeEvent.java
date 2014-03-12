package com.soql;

import java.util.EventObject;

import com.soql.SObjectTree.Selection;

public class SObjectTreeEvent extends EventObject {

	private static final long serialVersionUID = 9214616707563797427L;

	private Selection selection;
	
	public SObjectTreeEvent(Object source, Selection selection) {
		super(source);
		this.selection = selection;
	}
	public Selection getSelection() {
		return selection;
	}

}
