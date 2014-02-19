package soql;

import java.util.List;

import org.codehaus.jackson.JsonNode;

public class SObject{
	private JsonNode node;
	List<Field> fields = null;
	boolean loading = false;
	public SObject(JsonNode node){
		this.node = node;
	}
	public String getName(){
		return node.get("name").getTextValue();
	}
	public String toString(){
		return getName();
	}
	public boolean isCustom() {
		return node.get("custom").asBoolean();
	}
	public boolean isLoading(){
		return loading;
	}
}