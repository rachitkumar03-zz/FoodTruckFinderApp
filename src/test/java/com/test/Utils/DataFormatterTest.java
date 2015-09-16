package com.test.Utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.application.model.FoodTruck;
import com.application.utils.DataFormatter;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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
	
	@Test
	public void testGetFormattedJSonData() {
		List<FoodTruck> ftList = new ArrayList<>();
		FoodTruck ft = new FoodTruck();
		ft.setId(35467);
		ft.setAddress("1 Test Street");
		ftList.add(ft);
		ft = new FoodTruck();
		ft.setId(34578);
		ft.setAddress("2 Test Street");
		ftList.add(ft);
		
		JsonParser parser = new JsonParser();
		JsonArray jsonData = (JsonArray) parser.parse(dataFormatter.getFormattedJSonData(ftList));
		
		assertEquals("Size of JsonArray is incorrect", 2, jsonData.size());
		
		assertEquals("Data in Json Array 1 incorrect",35467, jsonData.get(0).getAsJsonObject().get("objectid").getAsInt());
		assertEquals("Data in Json Array 1 incorrect",34578, jsonData.get(1).getAsJsonObject().get("objectid").getAsInt());
		assertEquals("Data in Json Array 1 incorrect","1 Test Street", jsonData.get(0).getAsJsonObject().get("address").getAsString());
		assertEquals("Data in Json Array 1 incorrect","2 Test Street", jsonData.get(1).getAsJsonObject().get("address").getAsString());
		
	}
	
	@After
	public void destroy() {
		dataFormatter = null;
	}
}