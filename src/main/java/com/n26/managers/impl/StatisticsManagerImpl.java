package com.n26.managers.impl;


/**
 * bussiness of the calculation section
 * 
 * 
 */
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.controllers.helpers.TransactionHelper;
import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;
import com.n26.domain.base.DataContainer;
import com.n26.generic.GenericModule;
import com.n26.managers.StatisticsManager;

@Service
public class StatisticsManagerImpl extends GenericModule implements StatisticsManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7546715768278606707L;

	@Autowired
	private DataContainer dataContainer;
	@Autowired
	private TransactionHelper helper;

	@Override
	public void updateStatistics(Transaction transaction) {
		log.debug("--> updateStatistics ({})", transaction);

		Stastistics cs = new Stastistics();
		cs.addTransaction(transaction);

		LocalDateTime time = LocalDateTime.now(ZoneOffset.UTC);
		int index = helper.getTimeIndex(transaction.getTimestamp());

		Stastistics repo = dataContainer.getStatisticsArray()[index];
		// thread SAFE
		synchronized (repo) {
			cs.updateStastistics(repo);
			dataContainer.addStatisticsAtPos(cs, index);
		}

		log.debug("<-- updateStatistics ()");
	}

	@Override
	public Stastistics getStatistics() {
		log.debug("--> getStatistics ()");

		LocalDateTime time = LocalDateTime.now(ZoneOffset.UTC);

		Stastistics cs = new Stastistics();
		/*
		for (Stastistics statistic : dataContainer.getStatisticsArray()) {

			if (null != statistic && statistic.getCount() > 0
					&& helper.validateTransaction(time.toInstant(ZoneOffset.UTC).toEpochMilli(),
							statistic.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli())) {

				cs.updateStastistics(statistic);
			}
		}
		*/
		//REWRITE IN STREAMS IN ORDER TO BE o(1) and more clear.
		List<Stastistics> list = new ArrayList<Stastistics>(Arrays.asList(dataContainer.getStatisticsArray()));
		list.stream().filter(statistic -> statistic.getCount() > 0
				&& helper.validateTransaction(time.toInstant(ZoneOffset.UTC).toEpochMilli(),
						statistic.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli())).forEach(cs::updateStastistics);
		log.debug("<-- getStatistics ()");
		return cs;
	}

	@Override
	public void clearStatistics() {
		log.debug("--> clearStatistics ()");
		dataContainer.resetResuts();
		log.debug("<-- clearStatistics ()");
	}


	public TransactionHelper getHelper() {
		return helper;
	}

	public void setHelper(TransactionHelper helper) {
		this.helper = helper;
	}

	public DataContainer getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(DataContainer dataContainer) {
		this.dataContainer = dataContainer;
	}

	@Override
	protected Class<?> getLogClass() {
		return StatisticsManagerImpl.class;
	}

}
