package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        int result = Max.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenMax4To3Then4() {
        int result = Max.max(3, 4);
        assertThat(result, is(4));
    }
    @Test
    public void testOverloadOne() {
        int result = Max.max(4, 7, 9);
        assertThat(result, is(9));
    }
    @Test
    public void testOverloadTwo() {
        int result = Max.max(8, 11, 7);
        assertThat(result, is(11));
    }
    @Test
    public void testOverloadThree() {
        int result = Max.max(9, 7, 7, 17);
        assertThat(result, is(17));
    }
    @Test
    public void testOverloadFour() {
        int result = Max.max(33, 37, 3, 7);
        assertThat(result, is(37));
    }




}