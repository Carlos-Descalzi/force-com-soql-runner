package com.soql.client;

import java.util.ArrayList;
import java.util.List;

public class ApexError {

	private String message;
	private String errorCode;
	private List<String> fields = new ArrayList<String>();
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	
}
