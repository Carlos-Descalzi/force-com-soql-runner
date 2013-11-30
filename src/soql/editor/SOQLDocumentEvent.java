package soql.editor;


import java.io.File;
import java.util.EventObject;

public class SOQLDocumentEvent extends EventObject {

	private static final long serialVersionUID = -1480673365527546206L;
	private File file;

	public SOQLDocumentEvent(Object source) {
		this(source,null);
	}

	public SOQLDocumentEvent(Object source, File file) {
		super(source);
		this.file = file;
	}

	public File getFile() {
		return file;
	}

}
