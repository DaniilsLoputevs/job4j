package ru.job4j.exam.links;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LinksNewTest {
    private String[] strings;

    @Before
    public void setUp() {
        strings = new String[]{
                "111;123;222",
                "200;123;100",
                "300;;100",
                "111;000;337",
                ";;;",
                "444;545;767",
                "545;888;555",
                "444;888;222",
                "696;333;222"
        }; // 21 sub-strings
    }

    @Test
    public void testLinksHashMapWay() {
        Map<String, Set<String>> result = new LinksNew().grouping(strings);
        Map<String, Set<String>> expected = Map.of(
                "group3", Set.<String>of("444", "555", "545", "767", "888"),
                "group2", Set.<String>of(),
                "group1", Set.<String>of("", "000", "111", "100", "123",
                        "222", "200", "300", "696", "333", "444", "337", "888")
        );
        assertEquals(expected, result);
    }

}