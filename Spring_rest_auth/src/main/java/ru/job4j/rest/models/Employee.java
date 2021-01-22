package ru.job4j.rest.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * имя и фамилия, ИНН, дата найма, а также ссылку на список его аккаунтов
 * (в качестве аккаунтов используйте модель Person, в котором используются поля login и пароль),
 * которыми сотрудник пользуется для доступа к ресурсам корпоративной площадки.
 */
/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
/* Spring JPA */
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String inn; // 12 numbers
    
    @Column(name = "accounts")
//    @ElementCollection
    @OneToMany
//    private List<Integer> accounts;
    private List<Person> accounts;
    
}


