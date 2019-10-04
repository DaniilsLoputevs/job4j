package ru.job4j.condition;

public class TrgArea {
    // площадь треугольника
    public static double area(double a, double b, double c) {
        double p = (a + b + c) / 2;
        double sqrt = p * (p - a) * (p - b) * (p - c);
        return Math.sqrt(sqrt);
    }

    public static void main(String[] args) {
        double rsl = TrgArea.area(2, 2, 2);
        System.out.println("area (2, 2, 2) = " + rsl);
    }
}