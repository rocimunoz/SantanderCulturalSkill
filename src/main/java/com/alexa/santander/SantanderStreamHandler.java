package com.alexa.santander;


import com.alexa.santander.handlers.CancelAndStopIntentHandler;
import com.alexa.santander.handlers.CharacterIntentHandler;
import com.alexa.santander.handlers.CuriosityIntentHandler;
import com.alexa.santander.handlers.DictionaryIntentHandler;
import com.alexa.santander.handlers.FallBackIntentHandler;
import com.alexa.santander.handlers.HelpIntentHandler;
import com.alexa.santander.handlers.LaunchRequestHandler;
import com.alexa.santander.handlers.NoIntentHandler;
import com.alexa.santander.handlers.SessionEndedRequestHandler;
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
		new CancelAndStopIntentHandler(), //
		new CuriosityIntentHandler(), //
		new CharacterIntentHandler(), //
		new DictionaryIntentHandler(),
		new HelpIntentHandler(), //
		new LaunchRequestHandler(), //
		new SessionEndedRequestHandler(), //
		new FallBackIntentHandler(),
		new YesIntentHandler(),
		new NoIntentHandler())
		// Add your skill id below
			
		.withSkillId("amzn1.ask.skill.6ff36163-ff21-4a86-a3ef-eedc4320bc72")
		.build();
    }


    public SantanderStreamHandler()
    {
	super(getSkill());
    }

}