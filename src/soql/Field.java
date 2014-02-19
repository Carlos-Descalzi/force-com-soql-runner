package soql;

import org.codehaus.jackson.JsonNode;

public class Field {
	private JsonNode node;
	
	public Field(JsonNode node){
		this.node = node;
	}
	public String getName(){
		return node.get("name").getTextValue();
	}
	public String toString(){
		return getName() + " ("+node.get("type").getTextValue()+")";
	}
}