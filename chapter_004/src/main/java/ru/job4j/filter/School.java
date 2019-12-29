package ru.job4j.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {

    public static List<Student> collect(List<Student> students,
                                        Predicate<Student> predicate) {
        return students.stream().filter(
                predicate::test
        ).collect(Collectors.toList());
    }

    public static List<Student> levelOf(List<Student> students, int bound) {

        // ко каокой-то, причине, в таком способе ВСЁ не проходят фильтр больше 40
//        return students.stream()
//                .flatMap(Stream::ofNullable)
//                .sorted(new CompareUp())
//                .dropWhile(student -> student.getScore() >= bound)
//                .collect(Collectors.toList());

        return students.stream()
                .flatMap(Stream::ofNullable)
                .filter(student -> student.getScore() >= bound)
//                .sorted(new CompareUp()) - вроде он тут нужен, вроде нет.
                .takeWhile(student -> student.getScore() >= bound)
                .collect(Collectors.toList());
    }

}