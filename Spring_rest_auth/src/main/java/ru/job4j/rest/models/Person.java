package ru.job4j.rest.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

/* Lombok */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
/* Spring JPA */
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    
    public Person() {
    }
    
    public Person(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return id == person.id &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
