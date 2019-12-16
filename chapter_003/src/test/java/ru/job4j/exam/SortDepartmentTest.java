package ru.job4j.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortDepartmentTest {

    @Test // TreeSet сам по умолчания сортирует в нужном порядке
    public void sortUp() {
        Set<String> test = new TreeSet<String>(Arrays.asList(
                "K1\\SK1",  // 5
                "K1",  // 1
                "K1\\SK1\\SSK1",  // 10
                "K2\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K1\\SK2",
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2"
        ));
        Set<String> compare = new TreeSet<String>(Arrays.asList(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        ));
        assertThat(test, is(compare));
    }

    @Test
    public void sortDown() {
        Set<String> test = new TreeSet<>(Arrays.asList(
                "K1\\SK1",  // 5
                "K1",  // 1
                "K1\\SK1\\SSK1",  // 10
                "K2\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K1\\SK2",
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2"

        ));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        ));
        test = SortDepartment.sortDown(test);
        assertThat(new ArrayList<>(test), is(expected));
    }



    @Test // Проверка индекса символов
    public void saf() {
        String test = "a\\b\\c";

        for (int i = 0; i < test.length(); i++) {
            System.out.println("index: " + i + " value: " + test.charAt(i));
        }
    }

    @Test // Ручная проверка. (в столбик)
    public void jtr() {
        Set<String> test = new TreeSet<>(Arrays.asList(
                "K1\\SK1",  // 5
                "K1",  // 1
                "K1\\SK1\\SSK1",  // 10
                "K2\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K1\\SK2",
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2"

        ));
        test = SortDepartment.sortDown(test);

        for (String s : test) {
            System.out.println(s);

        }
    }


}
