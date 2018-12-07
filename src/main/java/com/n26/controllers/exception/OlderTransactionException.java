package com.n26.controllers.exception;

public class OlderTransactionException extends Exception{ //204


	
	
	/**
	 * 	
	 * this exceptions is reased when getting an older transacton - HTTP error 204
	 * 
	 */
	 
	private static final long serialVersionUID = 472613264231745248L;

	public OlderTransactionException() {
		super();
	}

}
