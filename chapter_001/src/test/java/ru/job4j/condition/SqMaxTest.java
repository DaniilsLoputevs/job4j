package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class SqMaxTest {
    // Второй вариант можно легко проверить заменив max >> max_v2
    @Test
    public void maxFirst() {
        int first = 4, second = 2, third = 3, forth = 1;
        int expected = 4;
        int out = SqMax.max(first, second, third, forth);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void maxSecond() {
        int first = 1, second = 4, third = 3, forth = 2;
        int expected = 4;
        int out = SqMax.max(first, second, third, forth);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void maxThird() {
        int first = 1, second = 2, third = 4, forth = 3;
        int expected = 4;
        int out = SqMax.max(first, second, third, forth);
        Assert.assertEquals(expected, out);
    }
    @Test
    public void maxForth() {
        int first = 1, second = 2, third = 3, forth = 4;
        int expected = 4;
        int out = SqMax.max(first, second, third, forth);
        Assert.assertEquals(expected, out);
    }
}
