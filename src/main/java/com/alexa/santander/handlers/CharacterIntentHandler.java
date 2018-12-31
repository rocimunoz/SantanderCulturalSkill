package com.alexa.santander.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

public class CharacterIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("CategoryIntent")) || input.matches(intentName("CharacterIntent"));
		
	}

	public Optional<Response> handle(HandlerInput input) {
		
		String resultSpeechText = "prueba";
		ResponseBuilder responseBuilder = null;
//		
		
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
			//responseBuilder.withShouldEndSession(true);
			return responseBuilder.build();
			
		}else if (dialogState.getValue().equals(DialogState.COMPLETED.getValue())) {
			resultSpeechText = "completo";
			IntentRequest intentRequest = (IntentRequest) input.getRequest();
			Intent updatedIntent = intentRequest.getIntent();
			Map<String, Slot> slots = updatedIntent.getSlots();
			Slot slotCharacter = slots.get("character");
			if (slotCharacter.getValue().equals("aleatorio")) {
				resultSpeechText = "te tengo que dar uno aleatorio";
			}else {
				//resultSpeechText = "te tengo que hablar de " + slotCharacter.getValue();
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
