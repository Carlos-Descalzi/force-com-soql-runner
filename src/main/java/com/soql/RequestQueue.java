package com.soql;

import java.util.concurrent.ArrayBlockingQueue;

import com.soql.client.ApexRestClient;
import com.soql.client.ApexRestException;

public class RequestQueue implements Runnable {

	private ApexRestClient client;
	private Thread thread;
	private ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
	private RequestQueueExceptionLogger logger;
	public RequestQueue(ApexRestClient client,RequestQueueExceptionLogger logger){
		this.client = client;
		this.logger = logger;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void run(){
		while (true){
			try {
				Request request = queue.take();
				
				try {
					request.process(client);
				}catch (ApexRestException ex){
					logger.apexException(ex);
				}
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public void enqueue(Request request) throws InterruptedException{
		queue.put(request);
	}
	
	public boolean isDone(Request request){
		return !queue.contains(request);
	}
}
