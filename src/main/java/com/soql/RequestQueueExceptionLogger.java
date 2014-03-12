package com.soql;

import com.soql.client.ApexRestException;

public interface RequestQueueExceptionLogger {

	public void apexException(ApexRestException exception);
}
