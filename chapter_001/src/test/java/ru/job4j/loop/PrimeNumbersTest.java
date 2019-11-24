package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PrimeNumbersTest {

    @Test
    public void when5() {
        PrimeNumbers prime = new PrimeNumbers();
        int count = prime.calc(5);
        assertThat(count, is(3));
    }
    @Test
    public void when11() {
        PrimeNumbers prime = new PrimeNumbers();
        int count = prime.calc(11);
        assertThat(count, is(5));
    }
    @Test
    public void when1() {
        PrimeNumbers prime = new PrimeNumbers();
        int count = prime.calc(2);
        assertThat(count, is(1));
    }
    @Test
    public void when537() {
        PrimeNumbers prime = new PrimeNumbers();
        int count = prime.calc(537);
        assertThat(count, is(99));
    }
}
