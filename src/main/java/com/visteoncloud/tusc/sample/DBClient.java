package com.visteoncloud.tusc.sample;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.amazonaws.services.lambda.runtime.LambdaLogger;


public class DBClient {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
	static String tableName = System.getenv("DYNAMODB_TABLE");
	
	LambdaLogger logger;
	
	public DBClient(LambdaLogger l) {
		logger = l;
	}
	
	
	public void createItems(String user, HashMap<Integer, Float> data) {
		
		// create ArrayList<Item> to hold data we are going to insert in the DB
		ArrayList<Item> items = new ArrayList<Item>();
		
		// iterate over the data
		Iterator<Entry<Integer, Float>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Float> entry = it.next();
			Item item = new Item();
			item.withPrimaryKey("User", user, "Time", entry.getKey());
			item.withNumber("Value", entry.getValue());
			items.add(item);
		}
		
		// create write items
		TableWriteItems writeItems = new TableWriteItems(tableName).withItemsToPut(items);
		
		// do the write	
		BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(writeItems);
		do {
			
			Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
			if (outcome.getUnprocessedItems().size() == 0) {
				logger.log("All items have been written");
			} else {
				logger.log("Writing unprocessed items");
				outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
			}
			
		} while (outcome.getUnprocessedItems().size() > 0);
		
	}
	
}