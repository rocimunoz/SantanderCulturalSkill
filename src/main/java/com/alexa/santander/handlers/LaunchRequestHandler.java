package com.alexa.santander.handlers;


import static com.amazon.ask.request.Predicates.requestType;
import java.util.Optional;

import com.alexa.santander.utils.CommonUtils;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;


public class LaunchRequestHandler implements RequestHandler
{

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		
		return input.getResponseBuilder()//
				.withSpeech(CommonUtils.WELCOME_MESSAGE)//
				.withShouldEndSession(false)//
				.withSimpleCard("SantanderCultural", CommonUtils.WELCOME_MESSAGE)//
				.build();
	   
	}


}
