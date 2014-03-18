package com.soql;

import org.codehaus.jackson.JsonNode;

import com.soql.query.QueryObject;

@SuppressWarnings("serial")
public class SubqueryResultTableModel extends ResultTableModel {

	private boolean expanded = false;
	
	public SubqueryResultTableModel(JsonNode result, QueryObject fields) {
		super(result, fields);
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

}
