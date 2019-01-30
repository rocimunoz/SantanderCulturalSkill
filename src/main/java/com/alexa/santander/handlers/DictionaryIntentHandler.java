package com.alexa.santander.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.alexa.santander.configuration.DatabaseConfiguration;
import com.alexa.santander.dao.curiosity.CuriosityDao;
import com.alexa.santander.dao.curiosity.CuriosityItem;
import com.alexa.santander.dao.dictionary.DictionaryDao;
import com.alexa.santander.dao.dictionary.DictionaryItem;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DictionaryIntentHandler implements RequestHandler {

	

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("WordsIntent"));

		
	}

	public Optional<Response> handle(HandlerInput input) {
		String resultSpeechText = "comienza ejecución palabras";
		
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("word", "STARTED");
		
		
		input.getAttributesManager().setSessionAttributes(sessionAttributes);
		
		HandlerDispatcher dispatcher = new HandlerDispatcher();
		
		String wordsBBDD = dispatcher.manageDictionary();
		

		return input.getResponseBuilder().withSpeech(wordsBBDD)
				.withSimpleCard("SantanderCultural", wordsBBDD).withReprompt("¿Quieres otra?").build();

	}

}
