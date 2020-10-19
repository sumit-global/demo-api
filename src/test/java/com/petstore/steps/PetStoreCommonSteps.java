/**
 * @author Sumit
 * 
 * Common step definations class
 */

package com.petstore.steps;

import static com.petstore.base.TestBase.getProperty;

import java.util.List;

import org.testng.Assert;

import com.petstore.base.TestBase;
import com.petstore.utility.PropertyManager;
import com.petstore.utility.Utility;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PetStoreCommonSteps extends TestBase {

	@Given("^I have the endpoint as \"([^\"]*)\"$")
	public void createEndpoint(String endpoint) throws Throwable {
		setProperty("URL", Utility.createEndPoint(PropertyManager.getInstance().valueFromConfig(endpoint.split("/")[0]),
				PropertyManager.getInstance().valueFromConfig(endpoint.split("/")[1])));
	}

	@When("^I send the \"([^\"]*)\" request to \"([^\"]*)\" using$")
	public void sendReq(String methodType, String endpoint, DataTable table) {
		response = Utility.buildRequest(table, methodType);
	}

	@When("^I send the \"([^\"]*)\" request using request body as \"([^\"]*)\"$")
	public void sendPost(String methodType, String jsonBody, DataTable table) throws Throwable {
		setProperty("body", Utility.readJson(jsonBody));
		response = Utility.buildRequest(table, methodType);
	}

	@When("^I assert all pets status as \"([^\"]*)\"$")
	public void assertCondition(String value) {
		List<String> status = response.jsonPath().getList("status");
		for (String sta : status) {
			Assert.assertTrue(sta.equals(value));
		}
	}

	@When("^I assert value as \"([^\"]*)\" from response path \"([^\"]*)\"$")
	public void assertAddedPet(String value, String path) {
		Assert.assertTrue(
				response.jsonPath().getString(path).equals(getProperty(value) == null ? value : getProperty(value)));
	}

	@When("^I store the \"([^\"]*)\" of Pet$")
	public void storePetId(String path) {
		setProperty(path, response.jsonPath().getString(path));
	}
}
