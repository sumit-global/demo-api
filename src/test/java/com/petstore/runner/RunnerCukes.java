/**
 * @author Sumit
 * 
 * Runner class to run feature files
 */
package com.petstore.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(strict = true, features = "src/test/resources/features", glue = {
		"com.petstore.steps" }, tags = { "@API" }, monochrome = true, plugin = {
				"json:target/cucumber-json-report.json", "html:target/cucumber-html-report",
				"junit:target/cucumber.xml" })
public class RunnerCukes {

}
