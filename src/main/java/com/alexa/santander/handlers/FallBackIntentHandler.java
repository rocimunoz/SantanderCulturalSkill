package com.alexa.santander.handlers;


import static com.amazon.ask.request.Predicates.intentName;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;


public class FallBackIntentHandler implements RequestHandler
{

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.FallbackIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Lo siento Roci, no te entiendo!";
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("FuelApp", speechText).withReprompt(speechText).build();
	
	}


}
