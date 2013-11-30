package soql.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class OauthErrorResponse {
	private String error;
	private String errorDescription;

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@JsonProperty("error_description")
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}