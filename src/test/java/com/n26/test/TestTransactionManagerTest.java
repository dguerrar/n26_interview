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
import com.n26.domain.Stastistics;
import com.n26.domain.Transaction;
import com.n26.managers.TransactionManager;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes={Application.class})
public class TestTransactionManagerTest {

	@Autowired
	TransactionManager manager;
	
	
	
	@Test
	public void testAddTransactionOK() {
		
		Instant instant = Instant.now(); //time in UTC
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		
		
		Transaction t= new Transaction();
		t.setAmount(BigDecimal.TEN);
		t.setTimestamp(ldt);
		
		try {
			manager.addTransaction(t);
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		} 
	}
		
		@Test
		public void testAddTransactionKO1() {
			
			Instant instant = Instant.now(); //time in UTC
			LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			
			
			Transaction t= new Transaction();
			t.setAmount(BigDecimal.TEN);
			t.setTimestamp(ldt);
			try {
				manager.addTransaction(t);
			} catch (OlderTransactionException e) {
				assertTrue(false);
			} catch (InvalidTransactionException e) {
				assertTrue(false);
			} catch (ParseableTransactionRuntimeException e) {
				assertTrue(true);
			}
			
		}
		
		@Test
		public void testAddTransactionKO2() {
			
			Instant instant = Instant.now(); //time in UTC
			LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			
			ldt.plusDays(11); // a time in the future
			Transaction t= new Transaction();
			t.setAmount(BigDecimal.TEN);
			t.setTimestamp(ldt);
			
			try {
				manager.addTransaction(t);
			} catch (OlderTransactionException e) {
				assertTrue(true);
			} catch (InvalidTransactionException e) {
				assertTrue(false);
			} catch (ParseableTransactionRuntimeException e) {
				assertTrue(false);
			}
			
		}
	
		
		@Test
		public void testAddTransactionKO3() {
			

			Transaction t= new Transaction();

			
			try {
				manager.addTransaction(t);
			} catch (OlderTransactionException e) {
				assertTrue(false);
			} catch (InvalidTransactionException e) {
				assertTrue(false);
			} catch (ParseableTransactionRuntimeException e) {
				assertTrue(true);
			}
			
		}

		@Test
		public void testAddTransactionKO4() {
			
			Instant instant = Instant.now(); //time in UTC
			LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			
			ldt.plusDays(-11); // a time in the future
			Transaction t= new Transaction();
			t.setAmount(BigDecimal.TEN);
			t.setTimestamp(ldt);
			

			try {
				manager.addTransaction(t);
			} catch (OlderTransactionException e) {
				assertTrue(false);
			} catch (InvalidTransactionException e) {
				assertTrue(false);
			} catch (ParseableTransactionRuntimeException e) {
				assertTrue(true);
			}
			
		}
		
		
		@Test
		public void testGetStatisticsOK() {
			
			Instant instant = Instant.now(); //time in UTC
			LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			
			
			Transaction t= new Transaction();
			t.setAmount(BigDecimal.TEN);
			t.setTimestamp(ldt);
			
			try {
				manager.addTransaction(t);
				assertTrue(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertTrue(false);
			} 
			
			
			
			Stastistics st= manager.getStatistics();
			assertTrue(null!=st);
			
		}
		
		
		@Test
		public void testDeleteStatisticsOK() {
			manager.clearStatistics();
			assertTrue(true);
			
		}
}
