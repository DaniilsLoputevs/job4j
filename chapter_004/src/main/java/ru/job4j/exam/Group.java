package ru.job4j.exam;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Group {

    /**
     * Метод групперует студентов по секциям.
     *
     * @param students - List<Student> - Лист студентов.
     * @return Map<String, Set<String>> - Секция, Сет имён студентов(посещающие эту секцию).
     * P.s. написано с попытки 9-11-ой, Благодаря помощи из чата (Maksim Tiunchik)
     */
    public static Map<String, Set<String>> sections(List<Student> students) {
//        return students.stream()
//                .flatMap(e -> e.getUnits().stream()
//                        .map(s -> new Holder(s, students.get(0).getName() )) )
////                .map(u -> new Holder(x, students.getName())) )
//
//                .collect(Collectors.groupingBy(t -> t.)
//
//                )
        return students.stream()
                .flatMap(student -> student.getUnits().stream().
                        map(sections -> new Holder(sections, student.getName())))
                .collect(
                        Collectors.groupingBy(t -> t.key,
                                Collector.of(
                                        HashSet::new,
                                        (set, holder) -> set.add(holder.value),
                                        (left, right) -> {
                                            left.addAll(right);
                                            return left;
                                        }
                                )
                        )

                );
    }

}


//    public static Map<String, Set<String>> sections(List<Student> students) {
//
//        // return students.stream().flatMap(
//        //        // собираем объект Holder с unit и name
//        //).collect( // собираем карту
//        //        Collectors.groupingBy(t -> t.key, // определяем группировку
//        //                Collector.of(
//        //                        HashSet::new, // аккумулятор.
//        //                        (set, el) -> // как добавлять данные.
//        //                        (left, right) -> { left.addAll(right); return left; } // для агрегации.
//        //                )
//        //        )
//        //);
//
//        //v2
////        return students.stream()
////                .map(Student::getUnits)
////                .flatMap()
////                .collect()
//
//        //v3
////        return students.stream()
////                .flatMap(student -> student.getUnits().stream())
////                .map()
//
//        // stream().flatMap( student -> student.getList().stream().map(.......))
//
//
//        return students.stream()
//                .flatMap(e -> e.getUnits().stream()
//                                .map(s -> new Holder(s, students.get(0).getName() )) )
////                .map(u -> new Holder(x, students.getName())) )
//                .collect(Collectors.groupingBy(t -> t.)
//
//        )
//
////        return students.stream()
////                .flatMap(e -> e.getUnits().stream().
////                        map(x -> new Holder(x, e.getName())) )
////                .collect(
////                       Collectors.groupingBy(t -> t)
////                )
//
////        return students.stream()
////                .flatMap(student -> student.getUnits().stream()
////                        .map(section -> new Holder(student.getName(), section)))
////                .collect(
////                        Collectors.groupingBy(t -> t.)
////                )
//
//
//              //v5
////        return students.stream()
////                .flatMap(student -> Stream.of(student.getUnits().stream(), student.getName())
////                .collect(
////                        Collectors.groupingBy(t -> t.)
////                )
//
//                        //v4
////                return students.stream()
////                .map(student -> {return new Holder(student.getUnits().stream(), student.getName()); })
////                .collect(
////                        Collectors.groupingBy(t - > t)
////                )
//
//
//        //v1
////                student -> new Holder(student.getUnits().iterator().next(), student.getName())
////                student -> student.getUnits().stream();}
//
//
//
//
//    }
//
//
//