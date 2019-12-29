package ru.job4j.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    // начальный расклад дел (хотел нормально назать основной Лист)
    private ArrayList<Student> initCase;
    private Student a = new Student(30);
    private Student b = new Student(40);
    private Student c = new Student(50);
    private Student d = new Student(60);
    private Student e = new Student(70);
    private Student f = new Student(80);

    @Before
    public void init() {
        initCase = new ArrayList<>(Arrays.asList(
                a, b, c, d, e, f
        ));
    }
    // 4. В этом задании нужно использовать:
    // Stream API. метод filter
    // && collect(Collectors.toList())

    @Test // from 70 to 100
    public void classA() {
        List<Student> test = School.collect(initCase,
                student -> student.getScore() >= 70
        );
        assertThat(test, is(Arrays.asList(e, f)));
    }
    @Test // from 50 to 70
    public void classB() {
        List<Student> test = School.collect(initCase,
                student -> student.getScore() >= 50 && student.getScore() < 70
        );
        assertThat(test, is(Arrays.asList(c, d)));
    }
    @Test // from 0 to 50
    public void classC() {
        List<Student> test = School.collect(initCase,
                student -> student.getScore() < 50
        );
        assertThat(test, is(Arrays.asList(a, b)));
    }

    @Test // List of (more then bound)
    public void levelOf() {
        Student a = new Student("Григорий", 70);
        Student b = new Student("Анатолий", 40);
        Student c = new Student("Владимир", 80);

        List<Student> initCase = new ArrayList<>(Arrays.asList(
                a, null, b, null, c
        ));
        List<Student> test = School.levelOf(initCase, 50);
        assertThat(test, is(Arrays.asList(a, c)));
    }
}


