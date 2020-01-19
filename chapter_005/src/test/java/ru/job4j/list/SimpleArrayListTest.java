package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1); // third
        list.add(2); // second
        list.add(3); // first
    }

    @Test
    public void delete() {
        assertThat(list.delete(), is(3));
    }

    @Test
    public void get() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void size() {
        assertThat(list.getSize(), is(3));
    }
}
