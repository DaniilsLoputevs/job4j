package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExamTaskTest {

    @Test
    public void test1() {
        var obj = new ExamTask();
        assertThat(obj.contains1("mama", "amam"), is(true));
    }
    @Test
    public void test2() {
        var obj = new ExamTask();
        assertThat(obj.contains2("mama", "amam"), is(true));
    }
    @Test
    public void test2Negative() {
        var obj = new ExamTask();
        assertThat(obj.contains2("mama", "mamak"), is(false));
    }


}
