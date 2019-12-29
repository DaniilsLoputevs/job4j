package ru.job4j.filter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

    public static Map<Student, String> convert(List<Student> list) {
        return list.stream().distinct()
                .collect(Collectors.toMap(
                        student -> student,
                        Student::getName
                ));
    }
}