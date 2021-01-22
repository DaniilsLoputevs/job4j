package ru.job4j.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.rest.models.Employee;
import ru.job4j.rest.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
//    @Autowired
//    private RestTemplate rest;
    @Autowired
    EmployeeRepository employees;
    
    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(
                this.employees.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        var person = this.employees.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Employee()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
    
    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                this.employees.save(employee),
                HttpStatus.CREATED
        );
    }
    
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        this.employees.save(employee);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.employees.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
