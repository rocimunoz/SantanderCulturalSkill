package com.alexa.santander.handlers;



import com.alexa.santander.configuration.DatabaseConfiguration;
import com.alexa.santander.dao.curiosity.CuriosityDao;
import com.alexa.santander.dao.curiosity.CuriosityItem;
import com.alexa.santander.dao.dictionary.DictionaryDao;
import com.alexa.santander.dao.dictionary.DictionaryItem;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class HandlerDispatcher {

	//final static Logger LOGGER = Logger.getLogger(HandlerDispatcher.class);

	public final Regions REGION = Regions.US_EAST_1;

	private DictionaryDao dictionaryDao;
	private CuriosityDao curiosityDao;

	public String manageCuriosity() {
		String resultSpeechText = "";
		try {

			curiosityDao = new CuriosityDao(getInstanceDynamoDB());

			CuriosityItem item = curiosityDao.getRandomCuriosity();
			resultSpeechText = "Curiosidad sobre " + item.getName() + ": " + item.getDescription();

		} catch (Exception e1) {
			resultSpeechText = "Error 1. " + e1.getMessage();
		}

		return resultSpeechText;
	}

	public String manageDictionary() {

		String resultSpeechText = "";

		try {

			dictionaryDao = new DictionaryDao(getInstanceDynamoDB());

			DictionaryItem item = dictionaryDao.getRandomWords();
			resultSpeechText = item.getName() + ": " + item.getDescription();

		} catch (Exception e1) {
			//LOGGER.error("Error leyendo DynamoDB");
		}

		return resultSpeechText;
	}

	public AmazonDynamoDB getInstanceDynamoDB() {

		DatabaseConfiguration.initSecurityProperties();

		return AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();

	}

}
