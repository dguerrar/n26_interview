package com.n26.controllers.exception;

public class InvalidTransactionException extends Exception{ //400

	
	
	/**
	 * this exceptions is reased when getting invalid JSON - HTTP error 400
	 * 
	 */
	private static final long serialVersionUID = -4841589304905821928L;

	public InvalidTransactionException() {
		super();
	}

}
