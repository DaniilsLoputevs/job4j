package ru.job4j.list;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        int[][] initCase = {
                {1, 2, 3, 4},
                {5, 6, 7},
                {8, 9},
                {10, 11, 12}
        };
        List<Integer> expectedCase = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        );
        List<Integer> test = new ConvertMatrix2List().toList(initCase);
        assertThat(test, is(expectedCase));
    }
}