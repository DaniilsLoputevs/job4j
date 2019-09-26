package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EndsWithTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'o'};
        boolean result = EndsWith.endsWith(word, post);
        assertThat(result, is(true));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'a'};
        boolean result = EndsWith.endsWith(word, post);
        assertThat(result, is(false));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse2() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'ф', 'о'};
        boolean result = EndsWith.endsWith(word, post);
        assertThat(result, is(false));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse3() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'l', 'o'};
        boolean result = EndsWith.endsWith(word, post);
        assertThat(result, is(true));
    }

    // Test for V2
    @Test
    public void whenStartWithPrefixThenTrueV2() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'o'};
        boolean result = EndsWith.endsWithV2(word, post);
        assertThat(result, is(true));
    }
    @Test
    public void whenNotStartWithPrefixThenFalseV2() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'a'};
        boolean result = EndsWith.endsWithV2(word, post);
        assertThat(result, is(false));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse1V2() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'ф', 'о'};
        boolean result = EndsWith.endsWithV2(word, post);
        assertThat(result, is(false));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse2V2() {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        char[] post = {'l', 'l', 'o'};
        boolean result = EndsWith.endsWithV2(word, post);
        assertThat(result, is(true));
    }

}