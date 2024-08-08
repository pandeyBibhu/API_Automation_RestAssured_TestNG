package com.restAssured.apiAuto_Actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restAssured.generateRequestPayload.addLibrary_payload;
import com.restAssured.pojo.AddLibraryPojo;
import com.restAssured.utilities.LoadProperties;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddLibraryAction {
	static AddLibraryPojo lib=new AddLibraryPojo();
	public static Response addBook() throws JsonProcessingException {
//        RestAssured.baseURI = baseURI;
        
        Response response = given().log().all().header("Content-Type", "application/json")
                .body(addLibrary_payload.generateAddLibraryRequestPayload())
                .when().post(LoadProperties.getEndpoint("addBook_post")).then().log().all().assertThat().statusCode(200).extract()
                .response();
        return response;
    }
    
    public static Response getBookWithAuthName(String authorName) throws JsonProcessingException {
        Response getResponse = given().log().all().header("Content-Type", "application/json")
                .queryParam("AuthorName", authorName)
                .when().get(LoadProperties.getEndpoint("getBook_get"))
                .then().log().all().assertThat().statusCode(200).extract().response();
        return getResponse;
    }
    
    public static Response getBookWithId(String id) throws JsonProcessingException {

        Response getResponse = given().log().all().header("Content-Type", "application/json")
                .queryParam("ID",id)
                .when().get(LoadProperties.getEndpoint("getBook_get"))
                .then().log().all().assertThat().statusCode(200).extract().response();
        return getResponse;
    }

    public static Response deleteBook(String id) {

        Response deleteResponse = given().log().all().header("Content-Type", "application/json")
                .body("{\r\n" +
                      "    \"ID\":\""+id+"\"\r\n" +
                      "}")
                .when().post(LoadProperties.getEndpoint("delBook_delete"))
                .then().log().all().assertThat().statusCode(200).extract().response();
        return deleteResponse;
    }
    
}
