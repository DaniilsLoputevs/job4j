package ru.job4j.tourist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ProfileTest {
    private ArrayList<Profile> initCase;
    private Address a = new Address("Новгород", "Ленина", 9, 4);
    private Address b = new Address("Москва", "Пушкинская", 18, 25);
    private Address c = new Address("Владивосток", "Флотская", 3, 38);
    @Before
    public void init() {
        initCase = new ArrayList<>(Arrays.asList(
               new Profile(a),
               new Profile(b),
               new Profile(c)
        ));
    }

    @Test
    public void addresses() {
        List test = Profile.collect(initCase);

        assertThat(test, is(Arrays.asList(a, b, c)));
    }
}
