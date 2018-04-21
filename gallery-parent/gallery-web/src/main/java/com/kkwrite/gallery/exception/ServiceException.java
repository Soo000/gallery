package com.kkwrite.gallery.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3790146115534626692L;

	public ServiceException(String errMsg, Exception e) {
		super(errMsg, e);
	}
	
	public ServiceException(String errMsg) {
		super(errMsg);
	}
	
}
