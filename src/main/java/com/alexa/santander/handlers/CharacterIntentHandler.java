package com.alexa.santander.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.alexa.santander.configuration.DatabaseConfiguration;
import com.alexa.santander.dao.character.CharacterDao;
import com.alexa.santander.dao.character.CharacterItem;
import com.alexa.santander.dao.curiosity.CuriosityDao;
import com.alexa.santander.dao.curiosity.CuriosityItem;
import com.alexa.santander.utils.CommonUtils;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.dialog.DelegateDirective;
import com.amazon.ask.response.ResponseBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class CharacterIntentHandler implements RequestHandler {

	private CharacterDao characterDao;
	private Regions REGION = Regions.US_EAST_1;

	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("CategoryIntent")) || input.matches(intentName("CharacterIntent"));
		
	}

	public Optional<Response> handle(HandlerInput input) {
		
		String resultSpeechText = "prueba";
		ResponseBuilder responseBuilder = null;
		
		
		DialogState dialogState = ((IntentRequest) input.getRequestEnvelope().getRequest()).getDialogState();
		if (dialogState == null) {
			resultSpeechText = "dialogo nulo";
		}
		else if (dialogState.getValue().equals(DialogState.STARTED.getValue())) {
			
			resultSpeechText = "inicio";
			responseBuilder = input.getResponseBuilder();
			IntentRequest intentRequest = (IntentRequest) input.getRequest();
			Intent updatedIntent = intentRequest.getIntent();
			
			responseBuilder.addDelegateDirective(updatedIntent);
			responseBuilder.withShouldEndSession(false);
			
		
			return responseBuilder.build();
						
			
		}else if (dialogState.getValue().equals(DialogState.IN_PROGRESS.getValue())) {
			resultSpeechText = "en progreso";
			
			responseBuilder = input.getResponseBuilder();
			IntentRequest intentRequest = (IntentRequest) input.getRequest();
			Intent updatedIntent = intentRequest.getIntent();
			
			responseBuilder.addDelegateDirective(updatedIntent);
			return responseBuilder.build();
			
		}else if (dialogState.getValue().equals(DialogState.COMPLETED.getValue())) {
			resultSpeechText = "completo";
			IntentRequest intentRequest = (IntentRequest) input.getRequest();
			Intent updatedIntent = intentRequest.getIntent();
			Map<String, Slot> slots = updatedIntent.getSlots();
			Slot slotCharacter = slots.get("character");
			if (slotCharacter.getValue().equals("aleatorio")) {
				resultSpeechText = "te tengo que dar uno aleatorio";
				
				DatabaseConfiguration.initSecurityProperties();
				
				AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
				 
				characterDao = new CharacterDao(client);
				
				CharacterItem item = characterDao.getRandomCharacter();
				resultSpeechText =  item.getName() + ": " + item.getDescription();
				
			}else {
				resultSpeechText = CommonUtils.CHARACTER_ATAULFO;
			}
		}
		else {
			resultSpeechText = dialogState.getValue().toString();
		}
			
		
		
		
		return input.getResponseBuilder().withSpeech(resultSpeechText)
				.withSimpleCard("SantanderCultural", resultSpeechText).build();

	}
}
