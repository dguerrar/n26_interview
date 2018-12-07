package com.n26.managers.impl;
/**
 * business code for the transactions storage and calculatnios
 * 
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;
import com.n26.generic.GenericModule;
import com.n26.managers.StatisticsManager;
import com.n26.managers.TransactionManager;
import com.n26.validators.TransactionValidator;


@Service
public class TransactionManagerImpl  extends GenericModule implements TransactionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8988676929806181938L;

	@Autowired
	private StatisticsManager statisticsManager;
	@Autowired
	private TransactionValidator validator;

	@Override
	public boolean addTransaction(Transaction transaction) throws OlderTransactionException,InvalidTransactionException,ParseableTransactionRuntimeException {
		log.debug("--> addTransaction ({})",transaction);
		validator.validate(transaction);
		statisticsManager.updateStatistics(transaction);
		
		log.debug("<-- addTransaction ()");
		return true;
	}


	@Override
	public Stastistics getStatistics()throws TransactionRuntimeException {
		log.debug("--> getStatistics ()");
		log.debug("<-- getStatistics ()");
		return statisticsManager.getStatistics();
	}
	
	
	@Override
	public boolean clearStatistics() throws TransactionRuntimeException {
		log.debug("--> clearStatistics ()");
		
		statisticsManager.clearStatistics();
		log.debug("<-- clearStatistics ()");
		return true;
	}
	
	@Override
	protected Class<?> getLogClass() {
		return TransactionManagerImpl.class;
	}



	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}



	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}

}
