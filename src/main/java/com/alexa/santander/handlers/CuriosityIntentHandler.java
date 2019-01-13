package com.alexa.santander.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.alexa.santander.dao.CuriosityItem;
import com.alexa.santander.configuration.DatabaseConfiguration;
import com.alexa.santander.dao.CuriosityDao;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;

import com.amazon.ask.model.Response;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


public class CuriosityIntentHandler implements RequestHandler {

	private CuriosityDao santanderCulturalDao;

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("CuriosityIntent"));

	}

	public Optional<Response> handle(HandlerInput input) {
		String resultSpeechText = "uno";
		Regions REGION = Regions.US_EAST_1;

		
		try {
			DatabaseConfiguration.initSecurityProperties();
			
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
			 
			santanderCulturalDao = new CuriosityDao(client);
			
			CuriosityItem item = santanderCulturalDao.getRandomCuriosity();
			resultSpeechText = "Curiosidad sobre " + item.getName() + ": " + item.getDescription();
			
		}catch(Exception e1) {
			resultSpeechText = "Error 1. " +  e1.getMessage();
		}
			
		return input.getResponseBuilder().withSpeech(resultSpeechText)
				.withSimpleCard("SantanderCultural", resultSpeechText).build();

	}
	

}
