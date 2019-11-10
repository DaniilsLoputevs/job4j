package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().matches(key)) {
                result.add(person);
                break;
            }
            if (person.getSurname().matches(key)) {
                result.add(person);
                break;
            }
            if (person.getPhone().matches(key)) {
                result.add(person);
                break;
            }
            if (person.getAddress().matches(key)) {
                result.add(person);
                break;
            }
        }
        return result;
    }
}