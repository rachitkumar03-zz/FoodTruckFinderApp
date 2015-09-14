package com.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.application.utils.DataFormatter;

/**
 * #Singleton Implementation
 * This is a Cache implementation to store Food Trucks to avoid calling the API every time a 
 * request is made
 * @author Rachit
 *
 */
public class FoodTruckCache extends HashMap<Integer, FoodTruck> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static FoodTruckCache instance;
	
	private String lastUpdated;
	
	/**
	 * Private constructor to prevent instantiation of the Cache Class
	 */
	private FoodTruckCache() {}
	
	/**
	 * Returns one single instance of the FoodTruck Cache throughout the span of the application
	 * @return
	 */
	public static FoodTruckCache loadCache() {
		// To make the instance of the Cache thread safe
		synchronized (FoodTruckCache.class) {
			if (instance == null) {
				// Lazily initializing instance of Cache
				instance = new FoodTruckCache();
			}
		}
		return instance;
	}
	
	/**
	 * This method is called to refresh cache data
	 * Currently done Every 3 hours
	 */
	public void refreshCacheData() {
		instance.putAll(new DataFormatter().getDataFromWebService());
	}
	
	/**
	 * Overloading put function on HashMap to take just an instance of the food Truck
	 * @param foodTruck
	 */
	public void put(FoodTruck foodTruck) {
		super.put(foodTruck.getId(), foodTruck);
	}
	
	/**
	 * Set the last update time of the Cache
	 */
	public void setUpdatedTimeStamp () {
		lastUpdated = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
	}
	
	/**
	 * Get the last update time of the Cache
	 * @return
	 */
	public String getLastUpdatedTimeStamp() {
		return lastUpdated;
	}	
}
