package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void convertList() {
        User one = new User("111");
        User two = new User("222");
        User three = new User("333");

        List<User> initCase = List.of(
                one, two, three
        );
        Map<Integer, User> test = UserConvert.process(initCase);

        Map expectedCase = Map.of(
                111, one,
                222, two,
                333, three
        );

        assertThat(test, is(expectedCase));
    }

}
