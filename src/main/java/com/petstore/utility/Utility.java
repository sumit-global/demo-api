/**
 * @author Sumit
 * 
 * Utility class contains resuable functions
 */

package com.petstore.utility;

import static com.petstore.base.TestBase.getProperty;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {

	/**
	 * Creates the end point by concating base_url and apiEndpoint.
	 *
	 * @param base_url    the base url
	 * @param apiEndpoint the api endpoint
	 * @return the string
	 */
	public static String createEndPoint(String base_url, String apiEndpoint) {
		return base_url.concat(apiEndpoint);
	}

	/**
	 * readJson --> Reads json Value as String
	 * 
	 * @param jsonName --> Name of json
	 * @return --> Json content as String
	 */
	public static String readJson(String jsonName) {
		try {
			URL file = Resources.getResource("jsonFolder/" + jsonName + ".json");
			String jsonString = Resources.toString(file, Charsets.UTF_8);
			return jsonString;
		} catch (Exception e) {

			System.out.println("Error while altering json : " + e);
			return null;
		}

	}

	/**
	 * Gets the random string.
	 *
	 * @return the random string
	 */
	public static String getRandomString() {
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder userName = new StringBuilder();
		Random rnd = new Random();
		while (userName.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * alphaNumeric.length());
			userName.append(alphaNumeric.charAt(index));
		}

		return userName.toString();
	}

	/**
	 * Gets the map from data table using key.
	 *
	 * @param table the table
	 * @param key   the key
	 * @return the map from data table using key
	 */
	public static Map<String, String> getMapFromDataTableUsingKey(DataTable table, String key) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < table.asLists().size(); i++) {
			if (table.asLists().get(i).get(0).equalsIgnoreCase(key)) {
				map.put(table.asLists().get(i).get(1),
						getProperty(table.asLists().get(i).get(2)) == null ? table.asLists().get(i).get(2)
								: getProperty(table.asLists().get(i).get(2)));
			}
		}
		return map;
	}
/**
 * Build api reauest and hit
 * @param table			datatable
 * @param methodType	http method type
 * @return				response of api
 */
	public static Response buildRequest(DataTable table, String methodType) {

		RequestSpecification spec = RestAssured.given().contentType(ContentType.JSON)
				.queryParams(getMapFromDataTableUsingKey(table, "query"))
				.pathParams(getMapFromDataTableUsingKey(table, "path"));
		switch (methodType.toLowerCase()) {
		case "get":
			return spec.get(getProperty("URL"));
		case "post":
			return spec.body(getProperty("body")).post(getProperty("URL"));
		case "put":
			return spec.body(getProperty("body")).put(getProperty("URL"));
		case "delete":
			return spec.delete(getProperty("URL"));
		case "patch":
			return spec.body(getProperty("body")).patch(getProperty("URL"));
		default:
			Assert.assertTrue(false, "Invalid method type passed: " + getProperty("URL"));
			return null;
		}
	}
}
