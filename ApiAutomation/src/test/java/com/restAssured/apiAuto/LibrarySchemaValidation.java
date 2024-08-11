package com.restAssured.apiAuto;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restAssured.apiAuto_Actions.AddLibraryAction;
import com.restAssured.utilities.LoadProperties;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LibrarySchemaValidation {
String id="";
	@BeforeSuite
	public void setup() {
		RestAssured.baseURI = LoadProperties.getBaseURI();
	}

	@Test
	public void validateGetBookWithIdSchema() throws JsonProcessingException {
		Response addResponse = AddLibraryAction.addBook();
		JsonPath addPath = new JsonPath(addResponse.asPrettyString());
		id = id+addPath.getString("ID");
		try {
			Response getBookWithIdResponse = AddLibraryAction.getBookWithId(id);

			getBookWithIdResponse.then().assertThat()
					.body(matchesJsonSchemaInClasspath("schema/getBookWithId_schema.json"));
			System.out.println(getBookWithIdResponse);
			System.out.println("===================================================");
			System.out.println("Schema validation passed successfully");
			System.out.println("===================================================");
		} catch (AssertionError e) {
			System.err.println("Schema validation failed: " + e.getMessage());
			throw e; // Rethrow the exception to ensure the test fails
		} catch (JsonSchemaValidationException e) {
			System.err.println("Schema validation failed: " + e.getMessage());
			throw e; // Rethrow the exception to ensure the test fails
		}

	}
	@AfterTest
	public void deleteBookId() {
		Response delResponse=AddLibraryAction.deleteBook(id);
	}
}
