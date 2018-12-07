package com.n26.validators.impl;
/**
 * validate implementation
 * 
 */
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


import org.springframework.stereotype.Component;

import com.n26.constants.Constants;

import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.domain.Transaction;
import com.n26.generic.GenericModule;
import com.n26.validators.TransactionValidator;

@Component
public class TransactionValidatorImpl extends GenericModule implements TransactionValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3191978578908174585L;

	@Override
	public void validate(Transaction transaction) throws OlderTransactionException ,ParseableTransactionRuntimeException{
		log.debug("-->validate (transaction {})",transaction);
		
		if(null== transaction.getAmount() || null==transaction.getTimestamp()) {
			throw new ParseableTransactionRuntimeException();
		} //--> not parseablw-->422
		
		//test time
		Instant instant = Instant.now(); //time in UTC

		LocalDateTime ldt =LocalDateTime.now(ZoneOffset.UTC);
		
		log.info("Transaction time {}, actual UTC time {} -instant {}", transaction.getTimestamp(), ldt,instant);
		
		LocalDateTime.now(ZoneOffset.UTC);
		
		
		
		Duration  timeInter = Duration.between(ldt,transaction.getTimestamp());
		
		if (timeInter.getSeconds()>0) {
			throw new ParseableTransactionRuntimeException();
		}//--> date in the future 422
		
		if (Math.abs(timeInter.getSeconds())>=Constants.MAX_TIME_INTER_TRANSACTIONS) {
			throw new OlderTransactionException();
		}//older than 60 seg --> 204
		
		log.debug("<--validate (transaction)");
	}

	@Override
	protected Class<?> getLogClass() {
		return TransactionValidatorImpl.class;
	}

}
