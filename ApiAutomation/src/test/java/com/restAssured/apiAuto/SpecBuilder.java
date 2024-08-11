package com.restAssured.apiAuto;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restAssured.apiAuto_Actions.AddLibraryAction;
import com.restAssured.generateRequestPayload.addLibrary_payload;
import com.restAssured.generateRequestPayload.deleteBook_payload;
import com.restAssured.pojo.AddLibraryPojo;
import com.restAssured.utilities.LoadProperties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	static AddLibraryPojo lib = new AddLibraryPojo();
	String id = "";

	@BeforeSuite
	public void setup() {
		RestAssured.baseURI = LoadProperties.getBaseURI();
	}

	@Test(priority = 1)
	public void addbookInLibrary() throws JsonProcessingException {
		String addResponse = addBook();
		JsonPath jsonPath = new JsonPath(addResponse.toString());
		String msg = jsonPath.getString("Msg");
		id = id + jsonPath.getString("ID");
		System.out.println("msg is::" + msg);
		AssertJUnit.assertEquals("PASSED", "successfully added", msg);

	}

	@Test(priority = 2)
	public void deleteBookFromLibrary() throws JsonProcessingException {
		Response deleteResponse = deleteBook(id);
	}

	public static String addBook() throws JsonProcessingException {
		String requestPayload = addLibrary_payload.generateAddLibraryRequestPayload();
		String basePath = LoadProperties.getEndpoint("addBook_post");
		String response = given().log().all().spec(createRequestSpec(requestPayload, basePath)).when().post().then()
				.log().all().spec(responseBuilder(200)).extract().asString();
		return response;
	}

	public static Response deleteBook(String id) throws JsonProcessingException {
		String basePath = LoadProperties.getEndpoint("delBook_delete");
		String requestPayload = deleteBook_payload.generateDeleteBookPayload(id);
		Response deleteResponse = given().log().all().spec(createRequestSpec(requestPayload, basePath)).when().post()
				.then().spec(responseBuilder(200)).log().all().extract().response();
		return deleteResponse;
	}

	public static RequestSpecification createRequestSpec(String requestPayload, String basePath)
			throws JsonProcessingException {

		RequestSpecBuilder builder = new RequestSpecBuilder();

		// Set the Base URI
		builder.setBody(requestPayload);

		// Set the Base Path
		builder.setBasePath(basePath);

		// Add Headers
		builder.addHeader("Content-Type", "application/json");

		// Build the Request Specification
		return builder.build();

	}

	public static ResponseSpecification responseBuilder(int code) {
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectStatusCode(code);
		builder.expectContentType("application/json");
		return builder.build();
	}

}
