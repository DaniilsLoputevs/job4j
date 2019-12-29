package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void convertList() {
        List<int[]> initCase = List.of(
                new int[]{1, 2},
                new int[]{3, 4, 5, 6}
        );
        List<Integer> test = ConvertList.convert(initCase);
        List<Integer> expectedCase = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        assertThat(test, is(expectedCase));
    }
}