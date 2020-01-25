package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SimpleSetTest {

    @Test
    public void add() {
        SimpleSet<Integer> set = new SimpleSet<>(10);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        Assert.assertThat(set.getSize(), is(2));
    }

    // проверяется в DymArrayListTest
//    @Test
//    public void iterator() {
//    }
}