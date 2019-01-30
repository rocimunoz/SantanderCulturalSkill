package com.alexa.santander.dao.curiosity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;


public class CuriosityDao {
	private final AmazonDynamoDB dynamoDbClient;
	private final DynamoDBMapper mapper;

	public CuriosityDao(AmazonDynamoDB dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
		mapper = new DynamoDBMapper(dynamoDbClient);
	}

	public int getCountCuriosities() {
		
		List<CuriosityItem> listCuriosities = mapper.scan(CuriosityItem.class, new DynamoDBScanExpression());
		
		return listCuriosities.size();
		
	}

	public CuriosityItem getRandomCuriosity() {

		int total = getCountCuriosities();
		int random = 1;
		if (total > 0) {
			random = (int) (Math.random() * total + 1);
		}

		CuriosityItem item = new CuriosityItem();
		item.setId(random);

		DynamoDBQueryExpression<CuriosityItem> queryExpression = new DynamoDBQueryExpression<CuriosityItem>()
				.withHashKeyValues(item);

		List<CuriosityItem> itemResult = mapper.query(CuriosityItem.class, queryExpression);

		if (itemResult != null) {
			return itemResult.get(0);
		} else
			return null;

	}

}
