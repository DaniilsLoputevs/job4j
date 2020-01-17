package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleArrayTest {
    private SimpleArray collection;

    @Before
    public void init() {
        collection = new SimpleArray<String>(10);
        collection.add("one");
    }


    @Test
    public void add() {
        collection.add("two");
        assertThat(collection.get(1), is("two"));
    }
    @Test
    public void addAll() {
        collection.addAll(List.of("two", "three"));
        assertThat(collection.get(0), is("one"));
        assertThat(collection.get(1), is("two"));
        assertThat(collection.get(2), is("three"));
    }
    @Test
    public void set() {
        collection.set(3, "three");
        assertThat(collection.get(3), is("three"));
    }
    @Test
    public void remove() {
        collection.add("two");
        collection.remove(0);
        assertThat(collection.get(0), is("two"));
    }
    @Test
    public void get() {
        assertThat(collection.get(0), is("one"));
    }
}