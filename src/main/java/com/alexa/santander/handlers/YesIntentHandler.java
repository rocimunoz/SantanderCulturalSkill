package com.alexa.santander.handlers;


import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.alexa.santander.utils.CommonUtils;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;


public class YesIntentHandler implements RequestHandler
{

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.YesIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		
		String resultIntent = "";
		
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

		if (sessionAttributes.get("word") != null && sessionAttributes.get("word").equals("STARTED")) {
            
			HandlerDispatcher dispatcher = new HandlerDispatcher();
			resultIntent = dispatcher.manageDictionary();
			return input.getResponseBuilder().withSpeech(resultIntent)
					.withSimpleCard("SantanderCultural", resultIntent).withShouldEndSession(true).build();
        
		}else if (sessionAttributes.get("curiosity")!=null && sessionAttributes.get("curiosity").equals("STARTED")) {
        	
        	HandlerDispatcher dispatcher = new HandlerDispatcher();
			resultIntent = dispatcher.manageCuriosity();
			return input.getResponseBuilder().withSpeech(resultIntent)
					.withSimpleCard("SantanderCultural", resultIntent).withShouldEndSession(true).build();
        }
        else {
        	resultIntent = "Lo siento. Se hap producido un error";
        	return input.getResponseBuilder().withSpeech(resultIntent)
    				.withSimpleCard("SantanderCultural", resultIntent).withShouldEndSession(true).build();
        }
		
		
				

		
		
		
			}


}
