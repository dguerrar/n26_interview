package com.n26.controllers.impl;
/**
 * 
 * implements the StatisticsController interface for the rest controller
 */
import com.n26.controllers.StatisticsController;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.controllers.helpers.TransactionHelper;
import com.n26.converter.StatisticConverter;
import com.n26.domain.Stastistics;
import com.n26.generic.GenericModule;
import com.n26.managers.TransactionManager;
import com.n26.constants.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class StatisticsControllerImpl extends GenericModule implements StatisticsController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 436976252932782548L;

	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private TransactionHelper helper; 
	
	@GetMapping(Constants.ENDPOINT_STATISTICS)
	public ResponseEntity getStatistics() throws TransactionRuntimeException{
		log.debug("--> getStatistics ()");

		Stastistics st = transactionManager.getStatistics();
		String res= helper.getStatsString(st);
		
		
		log.debug("<-- getStatistics ( {})",res);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
	}

	@Override
	protected Class<?> getLogClass() {
		return StatisticsControllerImpl.class;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
	
}
