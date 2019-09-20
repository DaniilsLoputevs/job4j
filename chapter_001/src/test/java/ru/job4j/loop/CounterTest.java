package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test // From 1 To 10 = 30
    public void SumEvenNumbers_30() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
    @Test // From 1 To 30 = 30
    public void SumEvenNumbers_240() {
        Counter counter = new Counter();
        int result = counter.add(1, 30);
        assertThat(result, is(240));
    }
}
// So long method's name