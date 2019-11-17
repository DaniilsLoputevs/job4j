package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void convertList() {
        List<User> list = new ArrayList<>();
        User one = new User("111");
        User two = new User("222");
        User three = new User("333");
        list.add(one);
        list.add(two);
        list.add(three);
        HashMap example = UserConvert.process(list);

        HashMap test = new HashMap();
        test.put(111, one);
        test.put(222, two);
        test.put(333, three);

        assertThat(example, is(test));
    }

}
