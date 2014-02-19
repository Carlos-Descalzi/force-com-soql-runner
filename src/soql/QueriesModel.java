package soql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

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
		Properties p = new Properties();
		if (file.exists()){
			try (InputStream in = new FileInputStream(file)){
				p.load(in);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			for (int i=0;;i++){
				String query = p.getProperty("q"+i);
				if (query == null){
					break;
				}
				queries.add(0,query);
			}
		}
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, queries.size()-1);
		for (ListDataListener listener:listeners){
			listener.intervalAdded(event);
		}
	}
	
	public void save(File file){
		Properties p = new Properties();
		
		for (int i=0,n=queries.size();i<n;i++){
			p.setProperty("q"+(n-i-1), queries.get(i));
		}
		try (OutputStream out = new FileOutputStream(file)){
			p.store(out, "");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
