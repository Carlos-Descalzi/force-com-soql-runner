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
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

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
	
	private String serviceURL;
	private String dataURL;
	private String queryURL;
	public ApexRestClient(String instance,String applicationPackage, String clientId, String clientSecret, String user, String password){
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.user = user;
		this.password = password;
		this.serviceURL = "https://"+instance+".salesforce.com/services/apexrest/";
		this.dataURL = "https://"+instance+".salesforce.com/services/data/v28.0/sobjects/";
		this.queryURL = "https://"+instance+".salesforce.com/services/data/v28.0/query/";
		
		if (StringUtils.isNotBlank(applicationPackage)){
			this.serviceURL += applicationPackage+"/";
		}
		
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
	
	public <T> T doPost(String request,Object data, Class<T> returnType)
		throws ApexRestException{
		
		try {
			HttpPost post =  createPost(serviceURL+request,data);
			return executeRequest(post, returnType);
		}catch(IOException ex){
			throw new ApexRestException(ex);
		}
	}
	public <T> T doPostData(String request,Object data, Class<T> returnType)
		throws ApexRestException{
		
		try {
			HttpPost post =  createPost(dataURL+request,data);
			return executeRequest(post, returnType);
		}catch(IOException ex){
			throw new ApexRestException(ex);
		}
	}
	
	private HttpPost createPost(String url,Object data) 
			throws JsonGenerationException, JsonMappingException, IOException{
		HttpPost post = new HttpPost(url);
		LOG.info(mapper.writeValueAsString(data));
		post.setEntity(new StringEntity(mapper.writeValueAsString(data)));
		post.setHeader("Content-type", "application/json");
		return post;
	}
	
	public <T> T doPatchData(String request,Object data, Class<T> returnType)
		throws ApexRestException{
		
		try {
			HttpPatch patch = new HttpPatch(dataURL+request);
			LOG.info(mapper.writeValueAsString(data));
			patch.setEntity(new StringEntity(mapper.writeValueAsString(data)));
			patch.setHeader("Content-type", "application/json");
			
			return executeRequest(patch, returnType);
		}catch(IOException ex){
			throw new ApexRestException(ex);
		}
	}
	
	private <T> T executeRequest(HttpUriRequest request,Class<T> returnType)
			throws ApexRestException{
		
		try {
			String result = execute(request);
			if (result != null){
				return mapper.readValue(result, returnType);
			}
			return null;
		}catch(IOException ex){
			throw new ApexRestException(ex);
		}
	}
			
	public <T> T doGet(String request,Class<T> returnType)
		throws ApexRestException {
		String result = doGet(request);
		if (StringUtils.isBlank(result)){
			return null;
		}
		try {
			return mapper.readValue(result, returnType);
		}catch (IOException ex){
			throw new ApexRestException(ex);
		}
	}
	
	private String doGet(String request) throws ApexRestException{
		return execute(new HttpGet(serviceURL+request));
	}
	private String doGetData(String request) throws ApexRestException{
		return execute(new HttpGet(dataURL+request));
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
	
	public <T extends SalesForceObject> T getObject(Class<T> objectType, String id)
		throws ApexRestException {
			String result = doGetData(getObjectType(objectType)+"/"+id);
			if (StringUtils.isBlank(result)){
				return null;
			}
			try {
				return mapper.readValue(result, objectType);
			}catch (IOException ex){
				throw new ApexRestException(ex);
			}
	}
	
	public <T extends SalesForceObject> QueryResult<T> doQuery(String query,Class<T> objectType)
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
			JavaType type = mapper
				.getTypeFactory()
				.constructParametricType(QueryResult.class, objectType);
			
			return mapper.readValue(result, type);
		}catch (IOException ex){
			throw new ApexRestException(ex);
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

	private String getObjectType(Class<?> type){
		if (type.isAnnotationPresent(ObjectType.class)){
			return type.getAnnotation(ObjectType.class).value();
		}
		return type.getSimpleName();
	}
	
	public String saveObject(SalesForceObject object)
		throws ApexRestException {
		SaveResponse response;
		
		if (StringUtils.isBlank(object.getId())){
			response = doPostData(getObjectType(object.getClass()),object,SaveResponse.class);
		} else {
			String id = object.getId();
			object.setId(null);
			object.setLastActivityDate(null);
			object.setLastModifiedBy(null);
			object.setLastModifiedDate(null);
			object.setCreateDate(null);
			object.setCreatedById(null);
			object.setDeleted(null);
			object.setSystemModstamp(null);
			response = doPatchData(getObjectType(object.getClass())+"/"+id, object, SaveResponse.class);
		}
		
		if (response != null){
			if (response.isSuccess()){
				return response.getId();
			}
			throw new ApexRestException(StringUtils.join(response.getErrors(),","));
		}
		return null;
	}

	public void init() throws Exception{
		getToken();
		
	}
}
