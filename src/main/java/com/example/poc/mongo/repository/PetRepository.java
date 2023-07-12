package com.example.poc.mongo.repository;

import com.example.poc.mongo.entity.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 10:34
 */
public interface PetRepository extends MongoRepository<Pet, String> {

    void deleteByName(String name);
}
