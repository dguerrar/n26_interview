package com.n26.managers;

import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;

public interface StatisticsManager {

	/**
	 * update the statitistic given a new transaction
	 * 
	 * @param transaction
	 */
	public void updateStatistics(Transaction transaction) ;
	
	/**
	 * get the lastes 60 seconds statistics
	 * 
	 */
	public Stastistics getStatistics() ;
	
	
	/**
	 * clear the statistics
	 * 
	 */
	public void clearStatistics();
}
