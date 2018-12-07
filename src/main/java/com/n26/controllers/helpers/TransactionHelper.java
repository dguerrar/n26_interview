/**
 * class to help in the calculations of the statistics
 * 
 * 
 */
package com.n26.controllers.helpers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.n26.constants.Constants;
import com.n26.converter.StatisticConverter;
import com.n26.domain.Stastistics;
import com.n26.generic.GenericModule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class TransactionHelper extends GenericModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1491548545132464353L;

	public String getStatsString(Stastistics st) {
		log.debug("-->getStatsString (stastistics {})",st);
		Gson gson = new GsonBuilder().registerTypeAdapter(Stastistics.class, new StatisticConverter()).create();

		log.debug("<--getStatsString ()");
		return gson.toJson(st);
	}

	public int getTimeIndex(LocalDateTime time) {
		log.debug("-->getTimeIndex (LocalDateTime {})",time);
		log.debug("<--getTimeIndex )");
		return ((int) time.toInstant(ZoneOffset.UTC).toEpochMilli() / Constants.MAX_TIME_INTER_TRANSACTIONS_INTERVAL)
				% Constants.MAX_TIME_INTER_TRANSACTIONS;
	}

	public boolean validateTransaction(long currentTime, long time) {
		log.debug("-->validateTransaction (currentTime {}   time {})",currentTime,time);
		log.debug("-->validateTransaction ()");
		return currentTime - time <= Constants.MAX_TIME_INTER_TRANSACTIONS_MILLIS;
	}

	@Override
	protected Class<?> getLogClass() {
		return TransactionHelper.class;
	}

}
