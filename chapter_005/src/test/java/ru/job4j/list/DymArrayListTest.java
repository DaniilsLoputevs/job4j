package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DymArrayListTest {

    private DymArrayList<Integer> list;
    private Iterator iterator;

    @Before
    public void setUp() {
        list = new DymArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        iterator = list.iterator();
    }

    @Test
    public void add() {
        assertThat(list.getSize(), is(3));
    }
    @Test
    public void get() {
        assertThat(list.get(0), is(1));
    }

    @Test
    public void iteratorHasNext() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void iteratorNext() {
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

}