package com.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.model.FoodTruck;
import com.application.model.FoodTruckCache;
import com.javadocmd.simplelatlng.Geohasher;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

/**
 * This class contains definition of methods that are required by the back-end
 * to process data
 * 
 * @author Rachit
 * 
 */
public class BaseUtils {

	LatLng currentLoction;
	double lat, lng;

	/**
	 * Checks if the request received is valid or not Sets the Latitude and
	 * Longitude from the request
	 * 
	 * @param currentPos
	 * @return
	 */
	public boolean isValidRequest(String currentPos) {
		List<String> strList = new ArrayList<>();
		for (String loc : currentPos.split(":"))
			strList.add(loc);
		if (strList.size() != 2)
			return false;
		lat = Double.valueOf(strList.get(0));
		lng = Double.valueOf(strList.get(1));
		return true;
	}

	/**
	 * Checks if the Cache needs update
	 * 
	 * @return
	 * @throws ParseException
	 */
	public boolean isCacheUpdateRequired() throws ParseException {

		if (FoodTruckCache.loadCache().getLastUpdatedTimeStamp() == null)
			return true;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		Date lastUpdatedDate = dateFormatter.parse(FoodTruckCache.loadCache()
				.getLastUpdatedTimeStamp());
		Date currentTimeStamp = Calendar.getInstance().getTime();
		float minutes = (currentTimeStamp.getTime() - lastUpdatedDate.getTime())
				/ (1000 * 60);
		return (minutes > BaseConstants.CACHE_REFRESH_MINUTES);
	}

	/**
	 * ==> Reloads Cache if required ==> Uses the Current Location and get
	 * nearest food trucks
	 * 
	 * @param currentPos
	 * @throws ParseException
	 */
	public String getResponseData() throws ParseException {
		currentLoction = new LatLng(lat, lng);
		if (isCacheUpdateRequired()) {
			DataFormatter dataFormatter = new DataFormatter();
			Map<Integer, FoodTruck> cacheMap = dataFormatter
					.getDataFromWebService();
			FoodTruckCache.loadCache().putAll(cacheMap);
			FoodTruckCache.loadCache().setGeoHashMap(
					generateGeoHashMap(cacheMap));
			FoodTruckCache.loadCache().setUpdatedTimeStamp();
			System.out.println("Cache Updated");
		}
		return new DataFormatter().getFormattedJSonData(getFoodTrucks());

	}

	/**
	 * Add eligible Food Trucks within a buffer distance to the Eligible List
	 * Buffer Distance defined in BaseUtils Class
	 * 
	 * @return
	 */
	public List<FoodTruck> getFoodTrucks() {
		List<FoodTruck> eligibleFoodTrucks = new ArrayList<>();
		for (FoodTruck ft : FoodTruckCache.loadCache().values()) {
			if (ft.getLatitude() != null && ft.getLongitude() != null) {
				LatLng ftLoc = new LatLng(ft.getLatitude(), ft.getLongitude());
				if (LatLngTool.distance(currentLoction, ftLoc, LengthUnit.MILE) <= BaseConstants.BUFFER_DISTANCE) {
					eligibleFoodTrucks.add(ft);
				}
			}
		}
		return eligibleFoodTrucks;
	}

	public Map<String, Integer> generateGeoHashMap(Map<Integer, FoodTruck> ftMap) {
		Map<String, Integer> geoMap = new HashMap<>();
		for (FoodTruck ft : ftMap.values()) {
			if (ft.getLatitude() != null && ft.getLongitude() != null)
				geoMap.put(computeGeoHash(ft.getLatitude(), ft.getLongitude()),
						ft.getId());
		}
		return geoMap;
	}

	/**
	 * Computes the GeoHashCode for a given Latitude and Longitude
	 * 
	 * @param a
	 * @param lng
	 * @return
	 */
	public String computeGeoHash(Double lat, Double lng) {
		return Geohasher.hash(new LatLng(lat, lng));
	}
}
