package com.n26.validators;

/**
 * 
 * validate the transaction. put an exception if somethinbg is wrong
 * 
 */
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.domain.Transaction;

public interface TransactionValidator {
	
	public void validate(Transaction transaction) throws OlderTransactionException,ParseableTransactionRuntimeException;

}
