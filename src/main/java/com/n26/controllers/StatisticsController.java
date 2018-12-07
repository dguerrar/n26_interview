package com.n26.controllers;


import org.springframework.http.ResponseEntity;

import com.n26.controllers.exception.TransactionRuntimeException;


public interface StatisticsController {

	/**
	 * controller restful for the statistics GET
	 * 
	 * @return
	 * @throws TransactionRuntimeException
	 */
	public ResponseEntity getStatistics()throws TransactionRuntimeException;
}
