package ru.job4j.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
       List<Student> test = initCase.stream().filter(
                student ->  student.getScore() >= 70
        ).collect(Collectors.toList());

       assertThat(test, is(Arrays.asList(e, f)));
    }
    @Test // from 50 to 70
    public void classB() {
        List<Student> test = initCase.stream().filter(
                student ->  student.getScore() >= 50 && student.getScore() < 70
        ).collect(Collectors.toList());

        assertThat(test, is(Arrays.asList(c, d)));
    }
    @Test // from 0 to 50
    public void classC() {
        List<Student> test = initCase.stream().filter(
                student ->  student.getScore() < 50
        ).collect(Collectors.toList());

        assertThat(test, is(Arrays.asList(a, b)));
    }
}


