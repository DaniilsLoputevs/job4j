package ru.job4j.extra;

import org.junit.Test;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class SpeedAnaliseTest {

//    метод Date.getTime() возвращает количество миллисекунд, прошедших с полуночи 1 января 1970 года
//    Date date1 = Date.getTime();
//    в конце метода Sout(date1 - Date.getTime()); или что то подобное, должен возвращать время в милисекундах

    @Test
    public void speed() {
//        System.out.println("1  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("2  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("3  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("4  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("5  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("6  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("7  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("8  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("9  times: " + String.format("%,12d", measure()) + " ms");
//        System.out.println("10 times: " + String.format("%,12d", measure()) + " ms");
    }

    @Test
    public void speedBody() {
        int times = 10;

        var completeTimes = new long[times];
        for (int i = 0; i < times; i++) {
            var dateStart = new Date().getTime();

//            measure();

            var dateFinish = new Date().getTime();
            completeTimes[i] = dateFinish - dateStart;
        }
        for (long num : completeTimes) {
            System.out.println(num);
        }

    }

    public long measure(Supplier method) {
        long st, en;
        st = new Date().getTime();
        for (int i = 0; i < 100; i++) {
            method.get();
        }
        en = new Date().getTime();
        return en - st;
    }

}