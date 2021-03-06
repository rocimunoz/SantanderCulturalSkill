package com.alexa.santander.handlers;


import static com.amazon.ask.request.Predicates.requestType;
import java.util.Optional;

import com.alexa.santander.utils.CommonUtils;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;


public class SessionEndedRequestHandler implements RequestHandler
{

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(SessionEndedRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		
		return input.getResponseBuilder().withSpeech(CommonUtils.BYE_MESSAGE)
				.withSimpleCard("SantanderCultural", CommonUtils.BYE_MESSAGE).build();
		
		
	}



}