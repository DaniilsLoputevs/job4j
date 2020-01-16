package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    private Iterator it;

    @Before
    public void init() {
        it = new EvenIterator(new int[] {4, 2, 1, 1, 8});
    }

    @Test
    public void hasNext() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void next() {
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(8));
    }
}