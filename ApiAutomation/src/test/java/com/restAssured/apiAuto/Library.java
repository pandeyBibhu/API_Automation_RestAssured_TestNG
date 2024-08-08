package com.restAssured.apiAuto;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restAssured.apiAuto_Actions.AddLibraryAction;
import com.restAssured.generateRequestPayload.addLibrary_payload;
import com.restAssured.utilities.LoadProperties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.util.Random;

public class Library {
	String id="";
	String author="";
	@BeforeSuite
	public void setup(){
		RestAssured.baseURI=LoadProperties.getBaseURI();
	}
	@Test(priority = 1)
	public void addBookIntoLibrary() throws JsonProcessingException {
		Response addResponse = AddLibraryAction.addBook();
		JsonPath jsonPath = new JsonPath(addResponse.asPrettyString());
		String msg = jsonPath.getString("Msg");
		id=id+jsonPath.getString("ID");
		System.out.println("msg is::" + msg);
		Assert.assertEquals("PASSED", "successfully added", msg);
	}

	@Test(priority = 2)
	public void getTheBookDetailsById() throws JsonProcessingException {
//		Response addResponse = AddLibraryAction.addBook();
		Response getResponse = AddLibraryAction.getBookWithId(id);
		JsonPath jsonPath = new JsonPath(getResponse.asPrettyString());
		author = author+jsonPath.getString("[0].author");
	    Assert.assertEquals("Author Name Matched","Bibhu Pandey",author);
	}
	@Test(priority = 3)
	public void getTheBookDetailsByAuthorName() throws JsonProcessingException {
		Response getResponse = AddLibraryAction.getBookWithAuthName(author);
	    JsonPath jsonPath=new JsonPath(getResponse.asPrettyString());
	    
	    
	}

	@Test(priority = 4)
	public void deleteBookFromLibrary() {
		Response deleteResponse = AddLibraryAction.deleteBook(id);
	}

}
