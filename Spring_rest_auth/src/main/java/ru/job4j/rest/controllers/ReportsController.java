package ru.job4j.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.models.Person;
import ru.job4j.rest.models.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/report")
public class ReportsController {
    @Autowired
    private RestTemplate rest;
    
    private static final String PERSON_URL = "http://localhost:8080/person/";
    private static final String PERSON_URL_ID = "http://localhost:8080/person/{id}";
    
    
    @GetMapping("/")
    public List<Report> findAll() {
        List<Report> rsl = new ArrayList<>();
        List<Person> persons = rest.exchange(
                PERSON_URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        for (Person person : Objects.requireNonNull(persons)) {
            Report report = Report.of(1, "First", person);
            rsl.add(report);
        }
        return rsl;
    }
    
    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(PERSON_URL, person, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }
    
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(PERSON_URL, person);
        return ResponseEntity.ok().build();
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(PERSON_URL_ID, id);
        return ResponseEntity.ok().build();
    }
}