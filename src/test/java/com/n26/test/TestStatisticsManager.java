package com.n26.test;

import static org.junit.Assert.assertTrue;

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
import com.n26.managers.StatisticsManager;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes={Application.class})
public class TestStatisticsManager {

	@Autowired
	StatisticsManager  manager;
	
	@Test
	public void testGetStatisticsOK1() {
		Stastistics st= manager.getStatistics();
		assertTrue(null!=st);
	}
	
	
	@Test
	public void testClearStatisticsOK1() {
		manager.clearStatistics();
	}
	
	
}
