package ru.job4j.filter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

    public static Map<Student, String> convert (List<Student> list) {
//        List<String> names;
//        for (Student student : list) {
//            names.add(student.getName());
//        }

        return list.stream().distinct().collect(
                Collectors.toMap(
                        student -> student,
                        Student::getName
                ));
    }
}

// System.out.println(
//        List.of(1, 1, 2, 2).stream().distinct().collect(
//                Collectors.toMap(
//                        e -> e,
//                        e -> e * e
//                ))
//);
//
//Вывод.
//{1=1, 2=4}
//
//Здесь
//
//                Collectors.toMap(
//                        e -> e,
//                        e -> e * e
//                )
//e - это элемент списка.
//Первый аргумент метода - это ключ карты
//Второй аргумент метода - это значение.