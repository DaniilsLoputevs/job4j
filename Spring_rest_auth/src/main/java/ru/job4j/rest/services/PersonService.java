package ru.job4j.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.rest.models.Person;
import ru.job4j.rest.repositories.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    
    @PostConstruct
    public void init() {
        // if store is empty
        if (!personRepository.findAll().iterator().hasNext()) {
            this.save( new Person(1, "first", "firstP"));
            this.save( new Person(2, "second", "secondP"));
            this.save( new Person(3, "third", "thirdP"));
        }
    }
    
    
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }
    
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }
    
    public Person save( Person person) {
        return this.personRepository.save(person);
    }
    
    public void delete(int id) {
        this.personRepository.deleteById(id);
    }
    
}
