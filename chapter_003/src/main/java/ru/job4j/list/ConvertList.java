package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {


    public static List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] arr : list) {
            for (int number : arr) {
                result.add(number);
            }
        }
        return result;
    }
}
