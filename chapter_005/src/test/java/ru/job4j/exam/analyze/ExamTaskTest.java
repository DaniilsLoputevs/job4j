package ru.job4j.exam.analyze;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExamTaskTest {

    @Test
    public void test1() {
        var obj = new ExamTask();
        assertTrue(obj.contains1("mama", "amam"));
    }

    @Test
    public void test2() {
        var obj = new ExamTask();
        assertTrue(obj.contains1("mama", "amam"));
    }

    @Test
    public void test2Negative() {
        var obj = new ExamTask();
        assertTrue(obj.contains1("mama", "amam"));
    }
}