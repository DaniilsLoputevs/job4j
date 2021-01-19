package ru.job4j.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@EnableJpaRepositories("ru.job4j.rest.repositories")
public class RestApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
    
}
