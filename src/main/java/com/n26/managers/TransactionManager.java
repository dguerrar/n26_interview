package com.n26.managers;

import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;

public interface TransactionManager {
	public boolean addTransaction(Transaction transaction) throws OlderTransactionException,InvalidTransactionException,ParseableTransactionRuntimeException;
	
	public Stastistics getStatistics() throws TransactionRuntimeException ;
	
	public boolean clearStatistics() throws TransactionRuntimeException ;
}
