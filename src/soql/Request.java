package soql;

import soql.client.ApexRestClient;
import soql.client.ApexRestException;

public interface Request {

	void process(ApexRestClient client) throws ApexRestException;

}
