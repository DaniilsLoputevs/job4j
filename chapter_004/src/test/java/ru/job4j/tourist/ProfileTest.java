package ru.job4j.tourist;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {
    private List<Profile> initCase;
    private Address a = new Address("Новгород", "Ленина", 9, 4);
    private Address b = new Address("Москва", "Пушкинская", 18, 25);
    private Address c = new Address("Владивосток", "Флотская", 3, 38);
    private Address d = new Address("Владивосток", "Флотская", 3, 38);
    private Address e = new Address("Амурск", "Восточная", 7, 26);
    @Before
    public void init() {
        initCase = new ArrayList<>(Arrays.asList(
               new Profile(a),
               new Profile(b),
               new Profile(c),
               new Profile(d),
               new Profile(e)
        ));
    }

    @Test
    public void addresses() {
        List<Address> test = Profile.collect(initCase);

        assertThat(test, is(Arrays.asList(e, c, b, a)));
    }
}
