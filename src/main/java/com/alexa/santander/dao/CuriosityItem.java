package com.alexa.santander.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Curiosidades")
public class CuriosityItem {

	private Integer id;
	private String name;
	private String description;
	
	@DynamoDBHashKey(attributeName="id")  
    public Integer getId() { return id; }
    public void setId(Integer id) {this.id = id; }
    
    @DynamoDBAttribute(attributeName="descripcion")  
    public String getDescription() {return description; }
    public void setDescription(String description) { this.description = description; }

    @DynamoDBAttribute(attributeName="nombre")  
    public String getName() {return name; }
    public void setName(String name) { this.name = name; }

    
}
