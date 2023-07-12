package com.example.poc.mongo.controller;

import com.example.poc.mongo.entity.Person;
import com.example.poc.mongo.entity.Pet;
import com.example.poc.mongo.repository.PersonRepository;
import com.example.poc.mongo.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pramosh Shrestha
 * @created 12/07/2023: 12:18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mongo")
public class MongoDemoController {

    private final MongoTemplate mongoTemplate;
    private final PetRepository petRepository;
    private final PersonRepository personRepository;

    @PostMapping(value = "/pet")
    public ResponseEntity<Pet> saveAPet(@RequestBody Pet pet) {
        return new ResponseEntity<>(petRepository.save(pet), HttpStatus.CREATED);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> saveAPerson(@RequestBody Person person) {
        Iterable<Pet> allById = petRepository.findAllById(person.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        person.setPets((List<Pet>) allById);
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @GetMapping(value = "/person")
    public ResponseEntity<List<Person>> getPerson() {
        return new ResponseEntity<>(mongoTemplate.findAll(Person.class), HttpStatus.OK);
    }

    @GetMapping(value = "/pet")
    public ResponseEntity<List<Pet>> getPet() {
        return new ResponseEntity<>(petRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable(value = "id") final String id) {
        return new ResponseEntity<>(petRepository.findById(id).orElse(new Pet().setName("Not Found")), HttpStatus.OK);
    }

    @PutMapping(value = "/pet/{id}")
    public ResponseEntity<Pet> updateAPet(
            @RequestBody Pet pet,
            @PathVariable(value = "id") final String id
    ) {
        Pet hello = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Hello"));
        hello.setName(pet.getName())
                .setId(id);
        return new ResponseEntity<>(petRepository.save(hello), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/pet/{id}")
    public ResponseEntity<Void> deleteAPet(@PathVariable(value = "id") final String id) {
        petRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
