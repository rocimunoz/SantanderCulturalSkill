package com.alexa.santander.handlers;


import static com.amazon.ask.request.Predicates.intentName;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;


public class CancelAndStopIntentHandler implements RequestHandler
{

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Adios";
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("FuelApp", speechText).build();
	
	}


}
