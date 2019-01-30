package com.alexa.santander.dao.dictionary;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;


public class DictionaryDao {
	private final AmazonDynamoDB dynamoDbClient;
	private final DynamoDBMapper mapper;

	public DictionaryDao(AmazonDynamoDB dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
		mapper = new DynamoDBMapper(dynamoDbClient);
	}

	public int getCountWords() {
		
		List<DictionaryItem> listWords = mapper.scan(DictionaryItem.class, new DynamoDBScanExpression());
		
		return listWords.size();
		
	}

	public DictionaryItem getRandomWords() {

		int total = getCountWords();
		int random = 1;
		if (total > 0) {
			random = (int) (Math.random() * total + 1);
		}

		DictionaryItem item = new DictionaryItem();
		item.setId(random);

		DynamoDBQueryExpression<DictionaryItem> queryExpression = new DynamoDBQueryExpression<DictionaryItem>()
				.withHashKeyValues(item);

		List<DictionaryItem> itemResult = mapper.query(DictionaryItem.class, queryExpression);

		if (itemResult != null) {
			return itemResult.get(0);
		} else
			return null;

	}

}
