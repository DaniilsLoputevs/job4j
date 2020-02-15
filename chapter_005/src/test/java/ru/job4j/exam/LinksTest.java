package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LinksTest {
    private String[] strings;

    @Before
    public void setUp() {
        strings = new String[] {
                "111;123;222", // 1
                "200;123;100", // 1
                "300;;100",    // 1
                "111;000;337", // 1
                ";;;",         // miss
                "444;545;767", // 2
                "545;888;555", // 3
                "444;888;222", // 2
                "696;333;222"  // 2
        };
    }

    @Test
    public void test() {
        var groupList = new Links().grouping(strings);
//        System.out.println(groupList);

        List<Links.Group> expectedList = List.of(
                new Links.Group(
                        new String[] {
                                "111;123;222",
                                "200;123;100",
                                "300;;100",
                                "111;000;337"},
                        4,
                        new String[] {"111", "123", "100"}
                ),
                new Links.Group(
                        new String[] {
                                "444;545;767",
                                "444;888;222",
                                "696;333;222"},
                        3,
                        new String[] {"444", "???", "222"}
                ),
                new Links.Group(
                        new String[] {
                                "545;888;555"},
                        1,
                        new String[] {"???", "???", "???"}
                )
        );
        assertThat(groupList.toString(), is(expectedList.toString()));
    }

}