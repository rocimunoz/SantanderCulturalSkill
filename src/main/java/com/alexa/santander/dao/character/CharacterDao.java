package com.alexa.santander.dao.character;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;


public class CharacterDao {
	private final AmazonDynamoDB dynamoDbClient;
	private final DynamoDBMapper mapper;

	public CharacterDao(AmazonDynamoDB dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
		mapper = new DynamoDBMapper(dynamoDbClient);
	}

	public int getCountCharacters() {
		
		List<CharacterItem> listCharacters = mapper.scan(CharacterItem.class, new DynamoDBScanExpression());
		
		return listCharacters.size();
		
	}

	public CharacterItem getRandomCharacter() {

		int total = getCountCharacters();
		int random = 1;
		if (total > 0) {
			random = (int) (Math.random() * total + 1);
		}

		CharacterItem item = new CharacterItem();
		item.setId(random);

		DynamoDBQueryExpression<CharacterItem> queryExpression = new DynamoDBQueryExpression<CharacterItem>()
				.withHashKeyValues(item);

		List<CharacterItem> itemResult = mapper.query(CharacterItem.class, queryExpression);

		if (itemResult != null) {
			return itemResult.get(0);
		} else
			return null;

	}

}
