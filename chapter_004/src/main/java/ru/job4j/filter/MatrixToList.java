package ru.job4j.filter;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixToList {

    public static List<Integer> convert(Integer[][] matrix) {
        return Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
