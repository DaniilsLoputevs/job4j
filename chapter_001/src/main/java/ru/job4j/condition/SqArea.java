package ru.job4j.condition;

public class SqArea {
    // Площадь треугольника по периметру и соотнощению чторон
    public static double square(int p, int k) {
        int h = p / 2 / (k + 1);
        int halfP = p / 2;
        int l = halfP - h;
//        System.out.println("L = " + l);
//        System.out.println("h = " + h);
        return l * h;
    }

    public static void main(String[] args) {
        double square = square(4, 1);
        System.out.println(" p = 4, k = 1, s = 1, real = " + square);
        double square1 = square(6, 2);
        System.out.println(" p = 6, k = 2, s = 2, real = " + square1);
        System.out.println();
        double square2 = square(8, 3);
        System.out.println(" p = 8, k = 3, s = 3, real = " + square2);
        double square3 = square(16, 7);
        System.out.println(" p = 16, k = 7, s = 7, real = " + square3);
        System.out.println();
        double square4 = square(8, 3);
        System.out.println(" p = 8, k = 3, s = 3, real = " + square4);
        double square5 = square(16, 7);
        System.out.println(" p = 16, k = 7, s = 7, real = " + square5);

    }
}