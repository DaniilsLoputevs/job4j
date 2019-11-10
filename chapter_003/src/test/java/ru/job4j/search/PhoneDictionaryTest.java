package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void findByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
    @Test
    public void findBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Arsentev");
        assertThat(persons.iterator().next().getPhone(), is("534872"));
    }
    @Test
    public void findByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("534872");
        assertThat(persons.iterator().next().getAddress(), is("Bryansk"));
    }
    @Test
    public void findByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Bryansk");
        assertThat(persons.iterator().next().getName(), is("Petr"));
    }

    @Test
    public void findByAddress3() {
        PhoneDictionary phones = new PhoneDictionary();
        Person one = new Person("Petr", "Arsentev", "534872", "Bryansk");
        Person two = new Person("Anya", "Melnjakova", "374927", "Volgograd");
        Person three = new Person("Ivan", "Kanavaev", "591462", "Volgograd");
        phones.add(one);
        phones.add(two);
        phones.add(three);

        List<Person> exp = new ArrayList<>();
        exp.add(two);
        exp.add(three);

        List<Person> persons = phones.find("Volgograd");
        assertThat(persons, is(exp));
    }
}