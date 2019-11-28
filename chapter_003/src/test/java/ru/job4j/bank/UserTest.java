package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    private User first;
    private User second;

    @Before
    public void init() {
        first = new User("Pasha", "Ru_900");
        second = new User("Anton", "Ru_450");
    }

    @Test
    public void equalsTest() {
        assertThat(first.equals(first), is(true));
    }
    @Test
    public void equalsFail() {
        assertThat(first.equals(second), is(false));
    }
}
