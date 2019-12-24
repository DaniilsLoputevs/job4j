package ru.job4j.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListToMapTest {
    private List<Student> initCase;
    private Map<Student, String> expectedCase;
    private Student a = new Student("Антон");
    private Student b = new Student("Кристина");
    private Student c = new Student("Жора");

    @Before
    public void init() {
    initCase = new ArrayList<>(Arrays.asList(
            a, b, c
    ));
    expectedCase = new HashMap<>();
    expectedCase.put(a, a.getName());
    expectedCase.put(b, b.getName());
    expectedCase.put(c, c.getName());
    }

    @Test
    public void ListToMap() {
        Map<Student, String> test = ListToMap.convert(initCase);

        assertThat(test, is(expectedCase));
    }
}
