package ru.job4j.filter;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {

    public static List convert(int[][] matrix) {

//        return Stream.of(matrix).flatMapToInt(Arrays::stream).collect(Collectors.toList());

//        return matrix.stream().flatMap(List::stream).collect(Collectors.toList());

// matrix.stream().flatMap(e -> e.stream()).collect(Collectors.toList())

        // заглушка
        return new ArrayList();

    }
}
