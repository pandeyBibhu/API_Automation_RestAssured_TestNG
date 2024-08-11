package com.restAssured.generateRequestPayload;
import com.restAssured.pojo.deleteBookPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
public class deleteBook_payload {
	
	public static String generateDeleteBookPayload(String id) throws JsonProcessingException {
		deleteBookPojo deleteBook=new deleteBookPojo();
		deleteBook.setID(id);
		ObjectMapper objectMapper = new ObjectMapper();
			
			
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deleteBook);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		
		System.out.println(generateDeleteBookPayload("12"));
	}
	
	
}
