package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefragmentTest {
    @Test
    public void notFirstNull() {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = Defragment.compress(input);
        String[] expected = {"I", "wanna", "be", "compressed", null, null, null};
        assertThat(compressed, is(expected));
    }
    @Test
    public void firstNull() {
        String[] input = {null, "I", "wanna", null, "be", null, "compressed"};
        String[] compressed = Defragment.compress(input);
        String[] expected = {"I", "wanna", "be", "compressed", null, null, null};
        assertThat(compressed, is(expected));
    }
    @Test
    public void oneMoreTime() {
        String[] input = {"We", "can", "use", null, "\"for\"", null, "in", "this", null, null, "task"};
        String[] compressed = Defragment.compress(input);
        String[] expected = {"We", "can", "use", "\"for\"", "in", "this", "task", null, null, null, null};
        assertThat(compressed, is(expected));
    }
}