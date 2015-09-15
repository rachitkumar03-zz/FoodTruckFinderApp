package com.test.Utils;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.application.model.FoodTruck;
import com.application.utils.DataFormatter;

public class DataFormatterTest {

	private DataFormatter dataFormatter;
	
	@Before
	public void setUp() {
		dataFormatter = new DataFormatter();
	}
	
	@Test
	public void testGetObjectFormattedData() {
		String sampleJson = " [{\"objectid\":\"305709\",\"dayshours\":\"Mo-Su:10AM-3PM/5PM-8PM\"},{\"objectid\":\"305710\",\"dayshours\":\"Mo-Su:10AM-3PM/5PM-8PM\"}] ";
		Map<Integer, FoodTruck> testMap = dataFormatter.getObjectFormattedData(sampleJson);
		
		assertEquals("Size of returned Map is incorrect", 2, testMap.size());
		
		assertEquals("Data is invalid",305709, testMap.get(305709).getId());
		assertEquals("Data is invalid",305710, testMap.get(305710).getId());
		
		assertEquals("Data is invalid","Mo-Su:10AM-3PM/5PM-8PM", testMap.get(305709).getDayshours());
		assertEquals("Data is invalid","Mo-Su:10AM-3PM/5PM-8PM", testMap.get(305710).getDayshours());
	}
	
	@After
	public void destroy() {
		dataFormatter = null;
	}
}