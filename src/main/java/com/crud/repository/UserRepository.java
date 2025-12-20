package com.crud.repository;

import com.crud.model.User;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final DynamoDbClient dynamoDb;
    private final String tableName = "Users";

    // Create DB Connection
    public UserRepository() {
        software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder builder = DynamoDbClient.builder();
        builder.endpointOverride(URI.create("http://localhost:8000"));
        builder.region(Region.US_EAST_1);
        builder.credentialsProvider(
                StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("dummy", "dummy")
                )
        );
        this.dynamoDb = builder
                .build();
    }

    // CREATE User
    public void createUser(@NotNull User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(user.getUserId()).build());
        item.put("name", AttributeValue.builder().s(user.getName()).build());
        item.put("email", AttributeValue.builder().s(user.getEmail()).build());

        dynamoDb.putItem(PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build());
    }

    // READ User
    public User getUser(String userId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());

        Map<String, AttributeValue> item = dynamoDb.getItem(GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build()).item();

        if (item == null || item.isEmpty()) return null;

        return new User(
                item.get("userId").s(),
                item.get("name").s(),
                item.get("email").s()
        );
    }

    // UPDATE User
    public void updateUser(User user) {
        Map<String, AttributeValueUpdate> updates = new HashMap<>();
        updates.put("name", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getName()).build())
                .action(AttributeAction.PUT)
                .build());
        updates.put("email", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getEmail()).build())
                .action(AttributeAction.PUT)
                .build());

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(user.getUserId()).build());

        dynamoDb.updateItem(UpdateItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .attributeUpdates(updates)
                .build());
    }

    // DELETE User
    public void deleteUser(String userId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());

        dynamoDb.deleteItem(DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build());
    }
}