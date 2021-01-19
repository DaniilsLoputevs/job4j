package ru.job4j.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.job4j.rest.repositories")
public class RestApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
        System.err.println("APP RUN");
    }
    
}
