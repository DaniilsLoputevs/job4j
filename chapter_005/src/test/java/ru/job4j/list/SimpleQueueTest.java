package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    private SimpleQueue<Integer> list;

    @Before // LIFO
    public void beforeTest() {
        list = new SimpleQueue<>();
        list.push(1); // third
        list.push(2); // second
        list.push(3); // first
    }

    @Test // LIFO
    public void poll() {
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(3));
        assertThat(list.getSize(), is(0));
    }

    @Test // LIFO
    public void combinePushAndPoll() {
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        list.push(5);
        list.push(6);
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(5));
        assertThat(list.poll(), is(6));

    }

}