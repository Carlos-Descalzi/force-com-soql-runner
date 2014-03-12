package com.soql;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class QueriesModel implements ListModel<String> {

	private List<String> queries = new ArrayList<>();
	
	private List<ListDataListener> listeners = new ArrayList<>();
	
	@Override
	public void addListDataListener(ListDataListener listener) {
		listeners.add(listener);
	}
	@Override
	public void removeListDataListener(ListDataListener listener) {
		listeners.remove(listener);
	}
	@Override
	public String getElementAt(int index) {
		return queries.get(index);
	}
	
	public void addQuery(String query){
		if (!queries.contains(query)){
			queries.add(0,query);
		}
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, 0);
		for (ListDataListener listener:listeners){
			listener.intervalAdded(event);
		}
		event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 1, queries.size()-1);
		for (ListDataListener listener:listeners){
			listener.contentsChanged(event);
		}
	}

	@Override
	public int getSize() {
		return queries.size();
	}

	public void load(File file){
		
		if (file.exists()){
			
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
				XPath xpath = XPathFactory.newInstance().newXPath();
				
				NodeList queriesNodes = (NodeList)xpath.evaluate("/queries/query",doc,XPathConstants.NODESET);
				
				for (int i=0;i<queriesNodes.getLength();i++){
					queries.add(queriesNodes.item(i).getFirstChild().getNodeValue());
				}
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, queries.size()-1);
		for (ListDataListener listener:listeners){
			listener.intervalAdded(event);
		}
	}
	
	public void save(File file){
		
		StringBuilder builder = new StringBuilder("<queries>\n");
		
		for (int i=0,n=queries.size();i<n;i++){
			builder
				.append("\t<query>")
				.append(queries.get(i).replace("<", "&lt;").replace(">", "&gt;"))
				.append("</query>\n");
		}
		
		builder.append("</queries>");
		
		try (FileWriter writer = new FileWriter(file,false)){
			writer.append(builder.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
