package ru.job4j.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.rest.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
