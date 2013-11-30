package soql.editor;


import java.util.EventListener;

public interface SOQLDocumentListener 
	extends EventListener{

	public void documentChanged(SOQLDocumentEvent evnet);
	public void docuemntSaved(SOQLDocumentEvent event);
}
