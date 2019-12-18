package ru.job4j;

public class MathUtil {
    public static double add(int left, int second) {
        return left + second;
    }

    public static double div(int left, int second) {
        return left / second;
    }

    // Приаемр:
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                MathUtil::add,
                System.out::println
        );
    }

//    Эти методы(add, div) можно заменить на интерфейс
//
//  BiFunction<Integer, Integer, Double> op,
//  два входящих параметра и один выходящий.
//
//  Если такое соответствие применимо, то мы можем указать использовать этот метод за место ламбды
//  Такой вызов называется ссылка на метод.
//
//
//     Синтаксис вызова:
// Важно, здесь не указываются параметры.
//
// Статический метод:
// (Имя класс)::(имя методы)
// Не статический метод:
// (имя переменной)::(имя метода)
//
//
// Пример(Статический):
//   Строка из main метода:
//   MathUtil::add
//
//  Пример(Не статического):
//  Пример в тесте.
//
//public class CalculatorTest {
//    @Test
//    public void whenAdd1Until3() {
//        Calculator calc = new Calculator();
//        List<Double> buffer = new ArrayList<>();
//        calc.multiple(
//                0, 3, 1,
//                MathUtil::add, //static call   -- ВАЖНО!!!
//                buffer::add // non-static call   - ВАЖНО!!!
//        );
//        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
//    }
//}

}