package ru.job4j.condition;

public class Triangle {

    public static boolean exist(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }
}
// return a + b > c // Значит if(a + b > c) {return true} else {return false}

// Обычно, если в контексте использкются тольк стороны, а вершины(углы) нет, от
// стороны именуют простыми буквами а не по буквам углов.


// Нужно проверить. что ab + ac > dc и ac + bc > ab и ab + ab + bc > ac. то этом случае треугольник существует.
// Так же мультиретурн это плохая практика
// В этом задании нужно использовать булеву логику и оператор || (логическое и).