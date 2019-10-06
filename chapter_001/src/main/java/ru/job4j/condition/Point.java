package ru.job4j.condition;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        double firstBrackets = x2 - x1;
        double resultX = pow(firstBrackets, 2);
        double secondBrackets = y2 - y1;
        double resultY = pow(secondBrackets, 2);
        return sqrt(resultX + resultY);
    }
    public double distance(Point that) {
        return sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2));
    }

    public static void main(String[] args) {
        // test v1
        double point0 = distance(10, 40, 20, 80);
        System.out.println("result (10, 40) to (20, 80) " + point0);
        double point1 = distance(70, 85, 160, 800);
        System.out.println("result (70, 85) to (160, 800) " + point1);
        double point2 = distance(352, 47, 731, 868);
        System.out.println("result (352, 47) to (731, 868) " + point2);
        System.out.println();
        //test v2
        Point a = new Point(10,40);
        Point b = new Point(20,80);
        double dist = a.distance(b);
        System.out.println("result (10, 40) to (20, 80) " + dist);
        Point a1 = new Point(70,85);
        Point b1 = new Point(160,800);
        double dist1 = a1.distance(b1);
        System.out.println("result (70, 85) to (160, 800) " + point1);
        Point a2 = new Point(352,47);
        Point b2 = new Point(731,868);
        double dist2 = a2.distance(b2);
        System.out.println("result (352, 47) to (731, 868) " + point2);

    }
}