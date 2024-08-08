package com.restAssured.apiAuto;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Response.*;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class AddNewPlace {
	String path="https://rahulshettyacademy.com";
 @Test
	public void addPlace() {
	 RestAssured.baseURI=path;
	 String response=
			 			given()
			 			.header("content-type","application/json")
			 			.queryParam("key", "qaclick123")
			 			.body("{\r\n"
			 					+ "  \"location\": {\r\n"
			 					+ "    \"lat\": -38.383494,\r\n"
			 					+ "    \"lng\": 33.427362\r\n"
			 					+ "  },\r\n"
			 					+ "  \"accuracy\": 50,\r\n"
			 					+ "  \"name\": \"Bibhu Pandey\",\r\n"
			 					+ "  \"phone_number\": \"9663917652\",\r\n"
			 					+ "  \"address\": \"Chennai\",\r\n"
			 					+ "  \"types\": [\r\n"
			 					+ "    \"shoe park\",\r\n"
			 					+ "    \"shop\"\r\n"
			 					+ "  ],\r\n"
			 					+ "  \"website\": \"http://google.com\",\r\n"
			 					+ "  \"language\": \"en\"\r\n"
			 					+ "}\r\n"
			 					+ "").
			 			when().log().all()
			 			.post("maps/api/place/add/json").
			 			then().assertThat().statusCode(200).extract().response().asPrettyString();
			 			System.out.println(response);
			 			System.out.println("************GET Request***********");
			 			JsonPath jsonPath=new JsonPath(response);
			 			String placeId=jsonPath.getString("place_id");
			 			
			 			String getResponse= given().log().all()
			 									.header("content-type","application/Json")
			 									.queryParam("place_id", placeId)
			 									.queryParam("key", "qaclick123")
			 								.when().log().all()
			 									.get("/maps/api/place/get/json")
			 								.then().log().all().assertThat().statusCode(200).extract().asPrettyString();
			 			System.out.println(getResponse);
 }
}
