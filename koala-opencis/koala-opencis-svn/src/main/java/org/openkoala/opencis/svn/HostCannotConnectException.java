package org.openkoala.opencis.svn;

public class HostCannotConnectException extends BaseException {

	private static final long serialVersionUID = 2389484663291825626L;
	
	public HostCannotConnectException(){
		super();
	}
	
	public HostCannotConnectException(String message){
		super(message);
	}
	
	public HostCannotConnectException(String message, Throwable cause){
		super(message, cause);
	}

}