package com.restAssured.generateRequestPayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restAssured.pojo.AddLibraryPojo;
import com.restAssured.pojo.College;

public class addLibrary_payload {
	

	public static String generateAddLibraryRequestPayload() throws JsonProcessingException {
		College col = new College();

		col.setCollegeName("SVCE"); // Corrected this line
		col.setCollegeNumber("123456789");
		col.setAddress("Bangalore");

		AddLibraryPojo lib = new AddLibraryPojo();

		lib.setName("Selenium with JavaScript");
		lib.setAuthor("Bibhu Pandey");
		lib.setAisle(String.valueOf(generateRandom()));
		lib.setIsbn("auto");

		List<College> values = new ArrayList<>();
		values.add(col);

		lib.setCollege(values);

//        System.out.println(lib.toString());
		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lib);
//			System.out.println(jsonString);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lib);
	}

	public static int generateRandom() {
		Random random = new Random();
		int baseValue = 0; // Initial value

		int maxIncrement = 9999; // Maximum increment value

		int incrementValue = random.nextInt(maxIncrement) + 1; // Random increment between 1 and maxIncrement
		baseValue += incrementValue;
//		System.out.println(baseValue);

		return baseValue;

	}
	public static void main(String[] args) throws JsonProcessingException {
		generateAddLibraryRequestPayload();
	}

}
