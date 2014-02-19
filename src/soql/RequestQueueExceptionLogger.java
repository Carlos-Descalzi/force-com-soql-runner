package soql;

import soql.client.ApexRestException;

public interface RequestQueueExceptionLogger {

	public void apexException(ApexRestException exception);
}
