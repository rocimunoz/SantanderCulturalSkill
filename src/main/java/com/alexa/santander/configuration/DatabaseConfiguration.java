package com.alexa.santander.configuration;

public class DatabaseConfiguration {

	public static void initSecurityProperties() {
		
		System.setProperty("aws.accessKeyId", "");  
		System.setProperty("aws.secretKey", "");
		
	}
}
