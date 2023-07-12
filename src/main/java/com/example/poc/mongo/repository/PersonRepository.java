package com.example.poc.mongo.repository;

import com.example.poc.mongo.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 10:48
 */
public interface PersonRepository extends MongoRepository<Person, String> {
}
