package com.example.poc.mongo.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.AsynchronousSocketChannelStreamFactoryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 10:21
 */
@Primary
@Component
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Override
    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb://" + username + ":" + password + "@" + serverName + ":" + portNumber + "/" + databaseName + "?authSource=admin&readPreference=primary&directConnection=true&ssl=false");
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .streamFactoryFactory(AsynchronousSocketChannelStreamFactoryFactory.builder().build())
                .build();

        return MongoClients.create(clientSettings);
    }

    @Override
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
        MongoTemplate mongoTemplate = new MongoTemplate(databaseFactory, converter);
        mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

        return mongoTemplate;
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.poc.mongo");
    }

    @Override
    protected String getDatabaseName() {
        return "demo_collection";
    }
}
