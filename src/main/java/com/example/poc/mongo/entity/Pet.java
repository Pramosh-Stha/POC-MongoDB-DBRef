package com.example.poc.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 10:39
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "pet")
public class Pet {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field(value = "name")
    private String name;

    @DBRef
    @Field(value = "person")
    private Person person;
}
