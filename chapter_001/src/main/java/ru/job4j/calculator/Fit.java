package ru.job4j.calculator;

public class Fit {
    /*
        Идеальный вес для мужчин = (рост в сантиметрах – 100) · 1,15.
        Идеальный вес для женщин = (рост в сантиметрах – 110) · 1,15.
     */
    public static double manWeight(double height_CM) {
        double result = (height_CM - 100) * 1.15;
        return result;
    }

    public static double womanWeight(double height_CM) {
        double result = (height_CM - 110) * 1.15;
        return result;
    }

    public static void main(String[] args) {
        double man = manWeight(180);
        System.out.println("Man 180 is " + man);
        double woman = womanWeight(165);
        System.out.println("Woman 165 is " + woman);
    }

}