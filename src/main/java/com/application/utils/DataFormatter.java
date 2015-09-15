package com.application.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.model.FoodTruck;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.webservice.impl.WebServicesFactory;

/**
 * This class takes in a a raw Json type data and creates Food Truck objects with it
 * @author Rachit
 * 
 */
public class DataFormatter {

	private Gson gsonObj;

	/**
	 * Constructor sets the Gson object Converts objects based on the name in
	 * object class and Json (IDENTITY)
	 */
	public DataFormatter() {
		gsonObj = new GsonBuilder().setFieldNamingPolicy(
				FieldNamingPolicy.IDENTITY).create();
	}

	/**
	 * Calls the Webservice to get latest data
	 * @return
	 */
	public Map<Integer, FoodTruck> getDataFromWebService() {

		String rawData = WebServicesFactory.getWebServiceImplClass(
				BaseConstants.WEB_SERVICE_SF_FOOD_TRUCK).getData();
		return getObjectFormattedData(rawData);
	}

	/**
	 * Takes in a raw json string and converts it into a FoodTruck instance.
	 * Returns a map of Id to FoodTrucks
	 * @param rawData
	 * @return
	 */
	public Map<Integer, FoodTruck> getObjectFormattedData(String rawData) {
		Map<Integer, FoodTruck> tempCacheMap = new HashMap<>();

		JsonParser parser = new JsonParser();
		JsonArray jsonData = (JsonArray) parser.parse(rawData);

		FoodTruck foodTruck;
		for (JsonElement jsonElem : jsonData) {
			foodTruck = new FoodTruck();
			foodTruck = gsonObj.fromJson(jsonElem, FoodTruck.class);
			tempCacheMap.put(foodTruck.getId(), foodTruck);
		}
		return tempCacheMap;
	}

	/**
	 * Takes a list of FoodTrucks, converts it into a json and returns a String
	 * formatted Json
	 * @param eligibleFoodTrucks
	 * @return
	 */
	public String getFormattedJSonData(List<FoodTruck> eligibleFoodTrucks) {
		JsonArray jsonArr = new JsonArray();
		for (FoodTruck ft : eligibleFoodTrucks) {
			JsonElement jsonElem = gsonObj.toJsonTree(ft);
			jsonArr.add(jsonElem);
		}
		return jsonArr.toString();
	}
}
