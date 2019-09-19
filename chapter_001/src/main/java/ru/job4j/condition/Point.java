package ru.job4j.condition;

public class Point {
    public static double distance(int x1, int y1, int x2, int y2) {
        double firstBrackets = x2 - x1;
        double resultX = Math.pow(firstBrackets, 2);
        double secondBrackets = y2 - y1;
        double resultY = Math.pow(secondBrackets, 2);
        return Math.sqrt(resultX + resultY);
    }

    public static void main(String[] args) {
        double point0 = distance(10, 40, 20, 80);
        System.out.println("result (10, 40) to (20, 80) " + point0);
        double point1 = distance(70, 85, 160, 800);
        System.out.println("result (70, 85) to (160, 800) " + point1);
        double point2 = distance(352, 47, 731, 868);
        System.out.println("result (352, 47) to (731, 868) " + point2);
    }
}