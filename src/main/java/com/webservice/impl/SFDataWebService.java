package com.webservice.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.application.utils.BaseConstants;

/**
 * This class is the entry point to the SD Data Api
 * Fetches all the food truck details
 * @author Rachit
 *
 */
public class SFDataWebService {

	private URI endPoint;

	public SFDataWebService() {
		try {
			endPoint = new URI(BaseConstants.SFDATA_FOODTRUCK__ENDPOINT);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This makes a GET request to the SFDATA API to get latest food truck data
	 * @return
	 */
	public String getLatestData() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGetRequest = new HttpGet(endPoint);
		HttpResponse httpResponse = null;

		// Set Http Get request Headers
		httpGetRequest.setHeader("X-App-Token",
				BaseConstants.SFDATA_FOODTRUCK__APP_TOKEN);
		httpGetRequest.setHeader("Content-Type", "application/json");

		try {
			// Execute HTTP request
			httpResponse = httpClient.execute(httpGetRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		finally {
			/* When HttpClient instance is no longer needed,
			   shut down the connection manager to ensure
			   immediate deallocation of all system resources */
			httpClient.getConnectionManager().shutdown();
		}
		return "";
	}
}
