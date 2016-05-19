package com.soql.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class ApexRestClient {
	
	private static final Log LOG = LogFactory.getLog(ApexRestClient.class);

	private String clientId;
	private String clientSecret;
	private String user;
	private String password;

	private String token;
	private long tokenTime;

	@SuppressWarnings("deprecation")
	private DefaultHttpClient client;
	private ObjectMapper mapper;
	
	private String queryURL;
	private String serviceURL;
	
	@SuppressWarnings("deprecation")
	public ApexRestClient(String applicationPackage, String clientId, String clientSecret, String user, String password){
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.user = user;
		this.password = password;
		
		try {
			SSLSocketFactory socketFactory = new SSLSocketFactory(
					new TrustSelfSignedStrategy(),
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, socketFactory));
			
			ClientConnectionManager cm = new PoolingClientConnectionManager(registry);
			
			client = new DefaultHttpClient(cm);
			
		} catch (KeyManagementException | UnrecoverableKeyException
				| NoSuchAlgorithmException | KeyStoreException e) {
			throw new RuntimeException(e);
		}

		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	private String getToken()throws ApexRestException{
		connect();
		return token;
	}
	
	private String getQueryURL()throws ApexRestException{
		connect();
		return queryURL;
	}
	
	private String getServiceURL()throws ApexRestException{
		connect();
		return serviceURL;
	}
	
	private void connect()
		throws ApexRestException{
		if (token == null || queryURL == null || serviceURL == null || (System.currentTimeMillis() - tokenTime > (60*60*1000))){
			try {
				HttpPost post = new HttpPost("https://login.salesforce.com/services/oauth2/token");
				post.setEntity(new UrlEncodedFormEntity(Arrays.asList(
						new BasicNameValuePair("grant_type","password"),
						new BasicNameValuePair("client_id",clientId),
						new BasicNameValuePair("client_secret",clientSecret),
						new BasicNameValuePair("username",user),
						new BasicNameValuePair("password",password))));
				
				HttpResponse httpResponse = client.execute(post);
				
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					
					String body = EntityUtils.toString(httpResponse.getEntity());
					
					System.out.println(body);
					
					OauthResponse response = mapper.readValue(body, OauthResponse.class);
					
					token = response.getAccessToken();
					tokenTime = System.currentTimeMillis();
					
					this.queryURL = response.getInstanceUrl()+"/services/data/v28.0/query/";
					this.serviceURL = response.getInstanceUrl()+"/services/data/v29.0/";

				} else {
					StatusLine status = httpResponse.getStatusLine();
					System.out.println(status.getStatusCode()+":"+status.getReasonPhrase()+":"+httpResponse.getEntity().getContentType());
				}
			}catch (Exception ex){
				throw new ApexRestException(ex);
			}
		}
	}
	
	public JsonNode doQuery(String query)
		throws ApexRestException{
		String result;
		try {
			result = doGetQuery("?q="+URLEncoder.encode(query,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new ApexRestException(e);
		}
		
		if (StringUtils.isBlank(result)){
			return null;
		}

		try {
			return mapper.readTree(result);
		}catch (IOException ex){
			throw new ApexRestException(ex);
		}
		
	}

	private String doGetQuery(String request) throws ApexRestException{
		return execute(new HttpGet(getQueryURL()+request));
	}
	public JsonNode doServiceGet(String request)
		throws ApexRestException{
		String result;
		result = execute(new HttpGet(getServiceURL()+request));
		
		if (StringUtils.isBlank(result)){
			return null;
		}

		try {
			return mapper.readTree(result);
		}catch (IOException ex){
			throw new ApexRestException(ex);
		}
		
	}

	private String execute(HttpUriRequest request) throws ApexRestException {
		request.setHeader("Authorization", "Bearer "+getToken());
		try {
			
			LOG.info("Request:"+request);
			
			HttpResponse httpResponse = client.execute(request);
			
			StatusLine status = httpResponse.getStatusLine();
			
			int statusCode = status.getStatusCode();
			
			if (statusCode != HttpStatus.SC_OK 
				&& statusCode != HttpStatus.SC_CREATED
				&& statusCode != HttpStatus.SC_NO_CONTENT){
				
				String content = EntityUtils.toString(httpResponse.getEntity());
				
				LOG.error(content);
				
				ApexError[] error = mapper.readValue(content, ApexError[].class);
				
				throw new ApexRestException("Error "+status.getStatusCode()+":"+error[0].getErrorCode()+":"+error[0].getMessage());
			}
			
			if (httpResponse.getEntity() != null){
				String result = EntityUtils.toString(httpResponse.getEntity());
				
				LOG.info("Result:"+result);
				
				return result;
			}
			return null;
		}catch (IOException ex){
			throw new ApexRestException(ex);
		}
	}
	
	public void init() throws Exception{
		getToken();
		
	}
}
