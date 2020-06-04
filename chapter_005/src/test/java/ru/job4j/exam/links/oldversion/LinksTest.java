package ru.job4j.exam.links.oldversion;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LinksTest {
    private String[] strings;

    @Before
    public void setUp() {
        strings = new String[]{
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
                        new String[]{
                                "111;123;222",
                                "200;123;100",
                                "300;;100",
                                "111;000;337"},
                        4,
                        new String[]{"111", "123", "100"}
                ),
                new Links.Group(
                        new String[]{
                                "444;545;767",
                                "444;888;222",
                                "696;333;222"},
                        3,
                        new String[]{"444", "???", "222"}
                ),
                new Links.Group(
                        new String[]{
                                "545;888;555"},
                        1,
                        new String[]{"???", "???", "???"}
                )
        );
        assertThat(groupList.toString(), is(expectedList.toString()));
    }

    @Test
    public void asfdgfgnh() {
        var groupList = new Links().grouping(strings);
        List<String[]> list = List.of(strings).stream()
                .flatMap(s -> {

                    ArrayList<String[]> result = new ArrayList<>();
                    result.add(s.split(";"));
                    return result.stream();

                })
                .collect(Collectors.toList());
        System.out.println(list.toString());

        int i = 0;
        for (String[] array : list) {
            for (String string : array) {
                while (i < 3) {
                    i++;
                    System.out.println(string);

                }
                System.out.println();
            }
        }

        var setOne = Set.of("123", "111", "333");
        var setTwo = Set.of("123", "111", "333");
        setOne.size();
        System.out.println(setOne.equals(setTwo));


    }
}


