package com.soql.editor;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;

public class SOQLEditorKit extends StyledEditorKit {

	private static final long serialVersionUID = -6512650322790548776L;

	public SOQLEditorKit(){
	}
	
	@Override
	public Document createDefaultDocument() {
		return new SOQLDocument();
	}

	@Override
	public String getContentType() {
		return "text/logo";
	}

	@Override
	public void install(JEditorPane c) {
		super.install(c);
		c.setForeground(Color.WHITE);
		c.setFont(new Font("Monospace",Font.PLAIN,16));
		c.setCaretColor(Color.BLACK);
	}

}
