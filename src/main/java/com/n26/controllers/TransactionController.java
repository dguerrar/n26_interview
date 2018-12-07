package com.n26.controllers;

import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.domain.Transaction;

import org.springframework.http.ResponseEntity;


public interface TransactionController {

	/**
	 * controller restful for POST a new transaction
	 * 
	 * @param transaction
	 * @return
	 * @throws TransactionRuntimeException
	 * @throws ParseableTransactionRuntimeException
	 * @throws InvalidTransactionException
	 * @throws OlderTransactionException
	 */
	public ResponseEntity addTransaction(Transaction transaction) throws TransactionRuntimeException,
			ParseableTransactionRuntimeException, InvalidTransactionException, OlderTransactionException;
	
	
	/**
	 * controller POST in order to delete a transaction
	 * 
	 * @return
	 * @throws TransactionRuntimeException
	 */
	public ResponseEntity deleteTransaction() throws TransactionRuntimeException;
}
