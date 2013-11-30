package soql.client;

public class ApexRestException extends Exception {

	private static final long serialVersionUID = 523940283602811877L;

	public ApexRestException(String message){
		super(message);
	}
	
	public ApexRestException(Throwable cause){
		super(cause);
	}
}
