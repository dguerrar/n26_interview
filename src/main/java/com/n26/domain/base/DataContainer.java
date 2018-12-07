package com.n26.domain.base;
/**
 * stores the relationship of transaction in timeslopts of 1 second
 * 
 * 
 */
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.n26.constants.Constants;
import com.n26.domain.Stastistics;

@Component
public class DataContainer extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5897615187908867505L;

	private Stastistics statistics;
	private Stastistics[] statisticsArray;

	public Stastistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Stastistics statistics) {
		this.statistics = statistics;
	}

	private DataContainer() {
		super();
		resetResuts();
	}

	public Stastistics[] getStatisticsArray() {
		return statisticsArray;
	}

	public void setStatisticsArray(Stastistics[] statisticsArray) {
		this.statisticsArray = statisticsArray;
	}
	
	public Stastistics getStatisticsAtPos( int index) {
		return statisticsArray[index];
	}
	
	public void addStatisticsAtPos(Stastistics st, int index) {
		statisticsArray[index]= st;
	}

	
	//stores the info related to stastitics in a time -slot of 1second
	public void resetResuts() {
		this.statistics = new Stastistics();
		this.statisticsArray = new Stastistics[Constants.MAX_TIME_INTER_TRANSACTIONS];
		
		this.statisticsArray = IntStream
				.range(0, Constants.MAX_TIME_INTER_TRANSACTIONS)
				.mapToObj(i -> new Stastistics()).toArray(Stastistics[]::new);
				
	}

}
