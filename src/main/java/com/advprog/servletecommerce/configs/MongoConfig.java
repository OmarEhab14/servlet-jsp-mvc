package com.advprog.servletecommerce.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.concurrent.TimeUnit;

public class MongoConfig {

    private static MongoClient mongoClient;

    private static final String CONNECTION_STRING = System.getenv("MONGO_URI");

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {

            ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .applyToConnectionPoolSettings(builder ->
                            builder
                                    .maxSize(10)
                                    .minSize(2)
                                    .maxWaitTime(10, TimeUnit.SECONDS)
                    )
                    .applyToSocketSettings(builder ->
                            builder.connectTimeout(10, TimeUnit.SECONDS)
                    )
                    .build();

            mongoClient = MongoClients.create(settings);
        }

        return mongoClient;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}