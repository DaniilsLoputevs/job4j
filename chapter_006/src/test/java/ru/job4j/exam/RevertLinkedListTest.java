package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class RevertLinkedListTest {
    private RevertLinkedList<Integer> smallList;
    private RevertLinkedList<Integer> bigList;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        smallList = new RevertLinkedList<>();
        smallList.addAll(List.of(1, 2));
        bigList = new RevertLinkedList<>();
        bigList.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        iterator = smallList.iterator();
    }

    @Test
    public void whenAddThenIter() {
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        smallList.revert();
        iterator = smallList.iterator();
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));
    }

    @Test
    public void test3() {
        bigList.revert();
        iterator = bigList.iterator();
        assertThat(iterator.next(), is(10));
        assertThat(iterator.next(), is(9));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(0));
    }
}
