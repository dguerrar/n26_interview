package com.n26.controllers.exception;

public class ParseableTransactionRuntimeException extends Exception{

	/**
	 * 	
	 * this exceptions is reased when getting an not paseable transaction - HTTP error 422
	 * 
	 */
	private static final long serialVersionUID = 105223729154980805L;

	public ParseableTransactionRuntimeException() {
		super();
	}

}
