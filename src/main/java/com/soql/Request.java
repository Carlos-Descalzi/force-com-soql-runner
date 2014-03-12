package com.soql;

import com.soql.client.ApexRestClient;
import com.soql.client.ApexRestException;

public interface Request {

	void process(ApexRestClient client) throws ApexRestException;

}
