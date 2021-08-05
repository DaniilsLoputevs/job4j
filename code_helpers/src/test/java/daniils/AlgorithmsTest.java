package daniils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlgorithmsTest {
    @Test
    public void sumTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] arr1 = new int[]{1, 2};
        assertEquals(45, Algorithms.sum(arr, 0));
    }

    @Test
    public void qSort() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        Algorithms.qSort(arr, 0, arr.length - 1);
        var result = Algorithms.compare(arr, new int[]{1, 5, 7, 8, 9, 10});
        assertTrue(result);
    }
}