package com.n26.controllers.impl;
/**
 * 
 * implements the TransactionController interface for the rest controller
 */
import com.n26.constants.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.controllers.TransactionController;
import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.exception.TransactionBindingException;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.domain.Transaction;
import com.n26.generic.GenericModule;
import com.n26.managers.TransactionManager;


@RestController
public class TransactionControllerImpl extends GenericModule implements TransactionController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1583318923050605139L;

	@Autowired
	private TransactionManager transactionManager;

	@PostMapping(Constants.ENDPOINT_TRANSACTIONS)
	public ResponseEntity addTransaction(@RequestBody Transaction transaction) throws TransactionRuntimeException,
			ParseableTransactionRuntimeException, InvalidTransactionException, OlderTransactionException {
			log.debug("--> addTransaction ( {})", transaction);
			transactionManager.addTransaction(transaction);
			
			
			log.debug("<-- addTransaction ( {})");
			return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping(Constants.ENDPOINT_TRANSACTIONS)
	public ResponseEntity deleteTransaction() throws TransactionRuntimeException {
		log.debug("--> deleteTransaction ()");

		transactionManager.clearStatistics();
		log.debug("<-- deleteTransaction ( {})");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	protected Class<?> getLogClass() {
		return TransactionControllerImpl.class;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
