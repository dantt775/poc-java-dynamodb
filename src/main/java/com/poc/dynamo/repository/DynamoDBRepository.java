package com.poc.dynamo.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.xspec.PutItemExpressionSpec;
import com.poc.dynamo.util.DynamoConstants;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDBRepository {

    private DynamoDB dynamoDB;

    public DynamoDBRepository(AmazonDynamoDB amazonDynamoDB){
        this.dynamoDB=new DynamoDB(amazonDynamoDB);
    }


    public PutItemOutcome save2(String tableName, Item item){
        PutItemSpec putItemSpec = new PutItemSpec()
                .withItem(item)
                //prevents from replacing an existing item with the same hasKey, in this case, the field solicitationId
                .withConditionExpression("attribute_not_exists("+ DynamoConstants.TABLE_FIELD_SOLICITATIONID +")");


        Table table = getTable(tableName);
        return table.putItem(putItemSpec);
    }

    public PutItemOutcome save(String tableName, Item newItem){
        Table table = getTable(tableName);
        return table.putItem(newItem);
    }

    public UpdateItemOutcome update(String tableName, UpdateItemSpec itemUpdate){
        Table table = getTable(tableName);
        return table.updateItem(itemUpdate);
    }

    public ItemCollection<QueryOutcome> search(String tableName, QuerySpec querySpec){
        Table table = getTable(tableName);
        return table.query(querySpec);
    }

    public Item findItem(String tableName, GetItemSpec getItemSpec){
        Table table = getTable(tableName);
        return table.getItem(getItemSpec);

    }

    private Table getTable(String tableName){
        return dynamoDB.getTable(tableName);
    }
}
