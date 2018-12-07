package com.n26.controllers.exception;

public class TransactionBindingException extends Exception {

	/**
	 * 
	 * this exceptions is reased when getting an binding errors transaction - HTTP error 400
	 * 
	 */
	private static final long serialVersionUID = 2541143881972596334L;
	
	
	public TransactionBindingException() {
		super();
	}

}
