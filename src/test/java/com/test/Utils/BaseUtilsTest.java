package com.test.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.application.utils.BaseUtils;
import com.javadocmd.simplelatlng.Geohasher;
import com.javadocmd.simplelatlng.LatLng;

public class BaseUtilsTest {

	private BaseUtils baseUtils;
	
	@Before
	public void setup() {
		baseUtils = new BaseUtils();
	}
	
	@Test
	public void testIsValidRequest() {
		String positive = "1:2";
		String negative = "12";
		
		assertEquals("isValidRequest failed positive test",true, baseUtils.isValidRequest(positive));
		assertEquals("isValidRequest failed Negative test",false, baseUtils.isValidRequest(negative));
	}
	
	@Test
	public void testGeoHash() {
		Double lat = 37.887;
		Double lng = -122.3345;
		String hash = baseUtils.computeGeoHash(lat, lng);
		LatLng latLng = Geohasher.decode(hash);
		
		assertNotNull(hash);
		assertEquals("Latitude decoded incorrectly",lat, latLng.getLatitude(),0.0000000001);
		assertEquals("Latitude decoded incorrectly",lng, latLng.getLongitude(),0.0000000001);
	}
	
	@After
	public void destroy() {
		baseUtils = null;
	}
}
