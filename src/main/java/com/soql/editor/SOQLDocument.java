package com.soql.editor;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import com.soql.parser.SOQLLexer;

public class SOQLDocument extends  DefaultStyledDocument {

	private static final long serialVersionUID = -5965049277607984562L;

	private static final Set<String> endOfTokens = new HashSet<String>(Arrays.asList("\t"," ","\n","\r"));
	
	private File file;
	private boolean dirty;
	
	private List<SOQLDocumentListener> listeners = new ArrayList<SOQLDocumentListener>();
	
	public static SOQLDocument fromFile(File file)
		throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		SOQLDocument document = new SOQLDocument();
		for (String line = reader.readLine(); line != null;line = reader.readLine()){
			try {
				document.insertString(document.getLength(),line+"\r\n",new SimpleAttributeSet());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		document.file = file;
		document.dirty = false;
		reader.close();
		
		return document;
	}
	
	
	public SOQLDocument(){
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public File getFile(){
		return file;
	}
	
	public boolean isDirty(){
		return dirty;
	}
	
	public void save() throws IOException{
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		try {
			writer.append(getText(0,getLength()));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		writer.flush();
		writer.close();
		dirty = false;
		fireDocumentSaved(file);
	}


	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		try {
			
			super.insertString(offs, str, a);
			
			if (Boolean.TRUE.equals(a.getAttribute("preformatted"))){
				return;
			}
			
			int start = getStart(offs);
			int end = getEnd(offs+str.length());
			
			String text = getText(start,end-start);
			
			SOQLLexer lexer = new SOQLLexer(new ANTLRInputStream(new StringReader(text)));
			lexer.reset();
			Token token = lexer.nextToken();

			while (token.getType() != Token.EOF){
				Color color = Color.BLACK;
				switch (token.getType()){

				case SOQLLexer.STRING:
				case SOQLLexer.NUMERIC:
				case SOQLLexer.BOOLEAN:
				case SOQLLexer.OPERATOR:
					color = Color.BLUE;
					break;
				case SOQLLexer.AND:
				case SOQLLexer.OR:
				case SOQLLexer.NOT:
				case SOQLLexer.IN:
				case SOQLLexer.SELECT:
				case SOQLLexer.WHERE:
				case SOQLLexer.ORDER:
				case SOQLLexer.BY:
				case SOQLLexer.FROM:
					color = new Color(0x7f0055);
					break;
				}
				
				setColor(start+token.getStartIndex(),token.getText().length(),color);

				token = lexer.nextToken();
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		if (!dirty){
			dirty = true;
			fireDocumentChanged();
		}
	}
	
	private void setColor(int start, int length, Color color) {
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr, color);
		StyleConstants.setBold(attr, true);
		StyleConstants.setFontSize(attr, 14);
		setCharacterAttributes(start, length, attr, false);
	}
	
	private int getEnd(int offs) throws BadLocationException {
		int length = getLength();
		for (int i=offs;i<length;i++){
			char c = getText(i, 1).charAt(0);
			if (c == ' ' || c == '\t' || c == '\n'){
				return i;
			}
		}
		return length;
	}

	private int getStart(int offs) throws BadLocationException {
		for (int i=offs;i>=0;i--){
			if (endOfTokens.contains(getText(i, 1))){
				return i+1;
			}
		}
		return 0;
	}
	
	public void addLogoDocumentListener(SOQLDocumentListener listener){
		listeners.add(listener);
	}
	
	public void removeLogoDocumentListener(SOQLDocumentListener listener){
		listeners.remove(listener);
	}
	
	private void fireDocumentChanged(){
		SOQLDocumentEvent event = new SOQLDocumentEvent(this); 
		for (SOQLDocumentListener listener:new ArrayList<SOQLDocumentListener>(listeners)){
			listener.documentChanged(event);
		}
	}
	
	private void fireDocumentSaved(File file) {
		SOQLDocumentEvent event = new SOQLDocumentEvent(this,file); 
		for (SOQLDocumentListener listener:new ArrayList<SOQLDocumentListener>(listeners)){
			listener.documentChanged(event);
		}
	}

}
