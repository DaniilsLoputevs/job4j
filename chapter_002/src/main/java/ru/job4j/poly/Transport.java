package ru.job4j.poly;

public interface Transport {

    void move();

    void passengers(int passengers);

    double fillUp(double cost);
}

// В интерфейсе объявите три метода:
// ехать (без параметров, без возвращаемого типа),
// пассажиры (принимает число пассажиров, без возвращаемого типа),
// заправить (принимает кол-во топлива, возвращает цену).