package com.restAssured.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	private static Properties endpointProperties = new Properties();
	private static Properties configProperties = new Properties();
	static {
		try {
			FileInputStream configFileStream = new FileInputStream("src/test/resources/Properties/config.properties");
			configProperties.load(configFileStream);

			FileInputStream endpointFileStream = new FileInputStream("src/test/resources/Properties/endpoint.properties");
			endpointProperties.load(endpointFileStream);
		} catch (IOException e) {
			System.err.println("file cannot be loaded");
			e.printStackTrace();
		}
	}

	public static String getBaseURI() {

		return configProperties.getProperty("baseURI");

	}

	public static String getEndpoint(String Key) {
		return endpointProperties.getProperty(Key);
	}

//	public static void main(String[] args) {
//		System.out.println(getBaseURI());
//		System.out.println(getEndpoint("addBook_post"));
//		System.out.println(getEndpoint("getBook_get"));
//		System.out.println(getEndpoint("delBook_delete"));
//	}
}
