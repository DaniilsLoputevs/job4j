package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayIteratorTest {

    private SimpleArray collection;
    private Iterator iterator;
    @Before
    public void init() {
        collection = new SimpleArray(3);
        collection.addAll(List.of("one", "two", "three"));
        iterator = collection.iterator();
    }

    @Test
    public void hasNext() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void next() {
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.next(), is("three"));
    }

//    @Test // Ручной тест для exception
//    public void exception() {
//        assertThat(iterator.next(), is("one"));
//        assertThat(iterator.next(), is("two"));
//        assertThat(iterator.next(), is("three"));
//        assertThat(iterator.next(), is(new NoSuchElementException()));
//    }
}