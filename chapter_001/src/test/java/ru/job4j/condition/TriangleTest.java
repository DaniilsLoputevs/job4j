package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenExist() {
        boolean result = Triangle.exist(2.0, 2.0, 2.0);
        assertThat(result, is(true));
    }

    @Test
    public void whenExistFirst() {
        boolean result = Triangle.exist(7.0, 7.0, 7.0);
        assertThat(result, is(true));
    }

    @Test
    public void whenExistSecond() {
        boolean result = Triangle.exist(6767.0, 2.0, 2.0);
        assertThat(result, is(false));
    }

//    Interesting math moment, it could be a problem if it will use in specific context.
//    If remove one '9' after dot, all will work normal. It is a limit about normal work.
//    15 number after dot is normal, more not.
    @Test
    public void whenExistBroken() {
        boolean result = Triangle.exist(3.9999999999999999, 2.0, 2.0);
        assertThat(result, is(false));
    }
}

// don't need @Ignore annotation before class
