package soql;

import java.util.EventListener;

public interface SObjectTreeListener extends EventListener {

	public void queryCreationRequested(SObjectTreeEvent event);
}
