package com.soql;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JTextPane;

public class JTextPaneWriter extends Writer {

	private JTextPane textPane;
	private StringBuilder builder = new StringBuilder();
	
	public JTextPaneWriter(JTextPane textPane){
		this.textPane = textPane;
	}
	
	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
		textPane.setText(textPane.getText()+builder.toString());
		builder.setLength(0);
	}

	@Override
	public void write(char[] chars, int offset, int len) throws IOException {
		builder.append(chars, offset, len);
		flush();
	}



}
