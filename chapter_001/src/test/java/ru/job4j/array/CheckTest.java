package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {
    @Test // 3 elements = true
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test // 3 elements = false
    public void whenDataNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
    @Test // 8 elements = false
    public void whenDataNotMono() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true, true, false, true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}