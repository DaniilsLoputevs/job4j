package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distanceOne() {
        int x1 = 10;
        int y1 = 40;
        int x2 = 20;
        int y2 = 80;
        double expected = 41.23;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }
    @Test
    public void distanceTwo() {
        int x1 = 70;
        int y1 = 85;
        int x2 = 160;
        int y2 = 800;
        double expected = 720.64;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }
    @Test
    public void distance3DOne() {
        Point a = new Point(1, 2, 3);
        Point b = new Point(4, 5, 6);
        double expected = 5.19;
        double out = a.distance3D(b);
        Assert.assertEquals(expected, out, 0.01);
    }
    @Test
    public void distance3DTwo() {
        Point a = new Point(7, 7, 2);
        Point b = new Point(4, 7, 9);
        double expected = 7.61;
        double out = a.distance3D(b);
        Assert.assertEquals(expected, out, 0.01);
    }

}
