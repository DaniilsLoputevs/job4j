package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test // search value = 5 >> find index = 0
    public void whenArrayHas5Then0() {
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = FindLoop.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }
    @Test // search value = 7 >> find index = -1
    public void whenArrayHas7Then_Nothing () {
        int[] input = new int[] {5, 10, 3};
        int value = 7;
        int result = FindLoop.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }


    @Test // search value = 2 >> find index = 3
    public void whenFind3() {
        int[] input = new int[] {5, 2, 10, 2, 4};
        int value = 2;
        int start = 2;
        int finish = 4;
        int result = FindLoop.indexOf(input, value, start, finish);
        int expect = 3;
        assertThat(result, is(expect));
    }
    @Test // search value = 2 >> find index = 3
    public void whenFind7() {
        int[] input = new int[] {5, 2, 10, 2, 4, 7, 9, 0, 1, 5, 10, 7};
        int value = 7;
        int start = 2;
        int finish = 6;
        int result = FindLoop.indexOf(input, value, start, finish);
        int expect = 5;
        assertThat(result, is(expect));
    }
}