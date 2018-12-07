package com.n26.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.Application;
import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.helpers.TransactionHelper;
import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;
import com.n26.managers.TransactionManager;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = { Application.class })
public class TestConverter {

	@Autowired
	TransactionHelper helper;

	@Autowired
	TransactionManager manager;

	@Test
	public void testConverterOK() {

		Instant instant = Instant.now(); // time in UTC
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

		Transaction t = new Transaction();
		t.setAmount(BigDecimal.TEN);
		t.setTimestamp(ldt);

		Transaction t2 = new Transaction();
		t2.setAmount(BigDecimal.TEN);
		t2.setTimestamp(ldt);

		try {
			manager.addTransaction(t);
			manager.addTransaction(t2);
		} catch (Exception e) {
			assertTrue(false);
		} 
		
		
		Stastistics st = manager.getStatistics();
		assertTrue(null != st);

		String str = helper.getStatsString(st);

		assertTrue(null != str & str.length() > 0);

	}

}
