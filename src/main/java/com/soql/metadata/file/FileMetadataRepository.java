package com.soql.metadata.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.soql.metadata.MetadataRepository;
import com.soql.metadata.SObject;

public class FileMetadataRepository extends MetadataRepository{

	public void load(File rootDirectory){
		List<File> files = new ArrayList<File>();
		fetchSObjectFiles(rootDirectory,files);
		
		for (File file:files){
			try {
				objects.add(parseFile(file));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private SObject parseFile(File file) 
		throws Exception{
		
		Document doc = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(file);

		XPath xpath = XPathFactory.newInstance().newXPath();
		
		String name = StringUtils.substringBefore(file.getName(),".");
		
		DefaultSObjectImpl sobject = new DefaultSObjectImpl();
		
		sobject.setName(name);
		sobject.setCustom(name.toLowerCase().endsWith("__c"));
		
		NodeList nodeList = (NodeList)xpath.evaluate("/CustomObject/fields", doc, XPathConstants.NODESET);
		
		for (int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			
			DefaultFieldImpl field = new DefaultFieldImpl();
			
		}
		
		return sobject;
	}

	private void fetchSObjectFiles(File directory, List<File> files) {
		for (File file:directory.listFiles()){
			if (file.isDirectory()){
				fetchSObjectFiles(file, files);
			} else {
				files.add(file);
			}
		}
		
	}

	@Override
	protected boolean isLoading() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFilterCustomObjects(boolean selected) {
		// TODO Auto-generated method stub
		
	}
	
}
