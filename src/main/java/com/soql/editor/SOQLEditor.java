package com.soql.editor;


import javax.swing.JEditorPane;

public class SOQLEditor extends JEditorPane {

	private static final long serialVersionUID = -7320829986256809882L;

	public SOQLEditor(){
		setEditorKit(new SOQLEditorKit());
		setOpaque(true);
	}
}
