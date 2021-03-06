package com.alexa.santander.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.alexa.santander.configuration.DatabaseConfiguration;
import com.alexa.santander.dao.curiosity.CuriosityDao;
import com.alexa.santander.dao.curiosity.CuriosityItem;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;

import com.amazon.ask.model.Response;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class CuriosityIntentHandler implements RequestHandler {

	

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("CuriosityIntent"));

	}

	public Optional<Response> handle(HandlerInput input) {
		


		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		sessionAttributes.put("curiosity", "STARTED");

		input.getAttributesManager().setSessionAttributes(sessionAttributes);

		HandlerDispatcher dispatcher = new HandlerDispatcher();

		String curiosityBBDD = dispatcher.manageCuriosity();

		return input.getResponseBuilder().withSpeech(curiosityBBDD)
				.withSimpleCard("SantanderCultural", curiosityBBDD).withReprompt("¿Quieres otra?").build();

	}

}
