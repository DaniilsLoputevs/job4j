package ru.job4j.condition;

public class SqArea {
    public static double square(int p, int k) { // New Main
        int h = p / 2 / (k + 1);
        int halfP = p / 2;
        int l = halfP - h;
//        System.out.println("L = " + l);
//        System.out.println("h = " + h);
        return l * h;
    }
    // TestingVersion - for check result about h & L
//    public static double square1(int p, int k) {
//        double sumLH = p / 2;
//        double h = sumLH / (k + 1); // find height
//        double l = h * k; // find length
//        return l * h; // find s (squareArea)
//    }

    public static void main(String[] args) {
        // default tests
        double square = square(4, 1);
        System.out.println(" p = 4, k = 1, s = 1, real = " + square);
        double square1 = square(6, 2);
        System.out.println(" p = 6, k = 2, s = 2, real = " + square1);
        // added test for better testing
        System.out.println();
        double square2 = square(8, 3);
        System.out.println(" p = 8, k = 3, s = 3, real = " + square2);
        double square3 = square(16, 7);
        System.out.println(" p = 16, k = 7, s = 7, real = " + square3);

        // add extra test
//        System.out.println();
//        double test = square1(8, 3);
//        System.out.println(" p = 8, k = 3, s = 3, real = " + test);
//        double test1 = square1(16, 7);
//        System.out.println(" p = 16, k = 7, s = 7, real = " + test1);

    }
}