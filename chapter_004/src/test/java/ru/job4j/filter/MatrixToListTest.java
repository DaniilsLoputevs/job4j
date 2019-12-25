package ru.job4j.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixToListTest {
//    private List<List<Integer>> initCase;
    private int[][] initCase;
    private List expectedCase;

    @Before
    public void init() {
//        initCase = List.of(
//                List.of(1, 2),
//                List.of(3, 4),
//                List.of(5, 6)
//        );
        initCase = new int[][] {
                {1}, {2},
                {3}, {4},
                {5}, {6}};
        expectedCase = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4, 5, 6
        ));

    }

    @Test
    public void listToMap() {
        List test = MatrixToList.convert(initCase);

        assertThat(test, is(expectedCase));
    }
}
