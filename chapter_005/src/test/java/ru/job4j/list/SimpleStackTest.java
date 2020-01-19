package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> list;

    @Before // FIFO
    public void beforeTest() {
        list = new SimpleStack<>();
        list.push(1); // first
        list.push(2); // second
        list.push(3); // third - last
    }

    @Test // FIFO
    public void poll() {
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(1));
        assertThat(list.getSize(), is(0));
    }

    @Test
    public void get() {
        assertThat(list.peek(), is(1));
    }
}