package com.soql.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class OauthResponse {
	
	private String id;
	private long issuedAt;
	private String instanceUrl;
	private String signature;
	private String accessToken;
	private String tokenType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("issued_at")
	public long getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(long issuedAt) {
		this.issuedAt = issuedAt;
	}
	@JsonProperty("instance_url")
	public String getInstanceUrl() {
		return instanceUrl;
	}
	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}