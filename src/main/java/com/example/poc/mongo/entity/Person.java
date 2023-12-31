package com.example.poc.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 10:46
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "person")
public class Person {


    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field(value = "name")
    @Indexed(unique = true)
    private String name;

    @DBRef
    @Field(value = "pets")
    private List<Pet> pets;
}
