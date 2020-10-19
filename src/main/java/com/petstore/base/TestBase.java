/**
 * @author Sumit
 */
package com.petstore.base;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;


public class TestBase {

	public static final Map<String, String> propertyMap = new HashMap<>();
	public static Response response;

	public static String getProperty(String attribute) {
		return propertyMap.get(attribute);
	}

	public static void setProperty(String attribute, String value) {
		propertyMap.put(attribute, value);
	}

}
