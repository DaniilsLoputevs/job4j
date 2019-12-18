package ru.job4j;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    // функциональный интерфейс
    public interface Operation {
        double calc(int left, int right);
    }

    // Таблица умножения
    // v1
//    public void multiple(int start, int finish, int value, Operation op) {
//        for (int index = start; index != finish; index++) {
//            System.out.println(
//                    op.calc(value, index)
//            );
//        }
//    }
    // v2
    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

        // v1
//    public static void main(String[] args) {
//        Calculator calc = new Calculator();
//        calc.multiple(
//                0, 10, 2,
//                new Operation() {
//                    @Override
//                    public double calc(int left, int right) {
//                        return left * right;
//                    }
//                }
//        );
//    }

    // v2
//    public static void main(String[] args) {
//        Calculator calc = new Calculator();
//        calc.multiple(
//                0, 10, 2,
//                (value, index) -> {
//                    int result = value * index;
//                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
//                    return result;
//                }
//        );
//    }

    // v3
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(0, 10, 2, (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                },
                result -> System.out.println(result)
        );
    }







    /* Важно. Если функция не принимает параметры, то мы указываем пустые скобки.
    *  Если функция не возвращает параметры, мы не указываем return.
    */

    /*
              Функциональный интерфейс
    Их можно разделить на 4 группы.
      Consumer - принимает параметры, метод ничего не возвращает void.
      Function - принимает параметры и возвращает значение.
      Predicate - принимает параметры и возвращает значение типа boolean. Используется для фильтрации данных.
      Supplier - не принимает параметры, возвращает значение.
     */


}