package com.webservice.impl;

import com.application.utils.BaseConstants;

/**
 * This serves as a factory to return instance of a requested Web Service class
 * @author Rachit
 *
 */
public class WebServicesFactory {
	
	/**
	 * Returns the appropriate instance of the requested Web Service Impl class
	 * @param type
	 * @return
	 */
	public static WebServices getWebServiceImplClass(String type) {
		switch (type) {
			case  BaseConstants.WEB_SERVICE_SF_FOOD_TRUCK :
				return new SFDataWebService();
			default : 
				return null;
		}
	}

}
