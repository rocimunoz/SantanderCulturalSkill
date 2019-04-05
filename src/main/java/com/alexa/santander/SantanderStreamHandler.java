package com.alexa.santander;


import com.alexa.santander.handlers.CancelIntentHandler;
import com.alexa.santander.handlers.CuriosityIntentHandler;
import com.alexa.santander.handlers.DictionaryIntentHandler;
import com.alexa.santander.handlers.FallBackIntentHandler;
import com.alexa.santander.handlers.HelpIntentHandler;
import com.alexa.santander.handlers.LaunchRequestHandler;
import com.alexa.santander.handlers.NoIntentHandler;
import com.alexa.santander.handlers.SessionEndedRequestHandler;
import com.alexa.santander.handlers.StopIntentHandler;
import com.alexa.santander.handlers.YesIntentHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;


public class SantanderStreamHandler extends SkillStreamHandler
{

    @SuppressWarnings("unchecked")
    private static Skill getSkill()
    {

	return Skills.standard().addRequestHandlers(
		new StopIntentHandler(), //
		new CancelIntentHandler(), //
		new CuriosityIntentHandler(), //
		new DictionaryIntentHandler(),
		new HelpIntentHandler(), //
		new LaunchRequestHandler(), //
		new SessionEndedRequestHandler(), //
		new FallBackIntentHandler(),
		new YesIntentHandler(),
		new NoIntentHandler())
		.withSkillId("xxxxxxx")
		.build();
    }


    public SantanderStreamHandler()
    {
	super(getSkill());
    }

}