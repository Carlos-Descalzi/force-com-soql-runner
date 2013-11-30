package soql.client;

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

	private DefaultHttpClient client;
	private ObjectMapper mapper;
	
	private String queryURL;
	public ApexRestClient(String instance,String applicationPackage, String clientId, String clientSecret, String user, String password){
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.user = user;
		this.password = password;
		this.queryURL = "https://"+instance+".salesforce.com/services/data/v28.0/query/";
		
		
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
		
	private String getToken()
		throws ApexRestException{
		if (token == null || (System.currentTimeMillis() - tokenTime > (60*60*1000))){
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
					OauthResponse response = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), OauthResponse.class);
					
					token = response.getAccessToken();
					tokenTime = System.currentTimeMillis();
				}
				return token;
			}catch (Exception ex){
				throw new ApexRestException(ex);
			}
		}
		return token;
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
		return execute(new HttpGet(queryURL+request));
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
