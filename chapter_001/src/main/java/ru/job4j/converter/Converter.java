package ru.job4j.converter;

public class Converter {

    public static void main(String[] args) {
        doLogic();
        test();
    }

    public static int rubleToEuro(int value) {
        return value / 70;
    }
    public static int rubleToDollar(int value) {
        return value / 60;
    }
    public static int euroToRuble(int value) {
        return value * 70;
    }
    public static float euroToDollar(int value) {
        float changeRate = 1.1f; // from google for 12.09.2019 - 00:05 (timezone: GMT+1)
        return value * changeRate;
    }

    private static void doLogic() {
        // Ruble - Euro
        int euro = rubleToEuro(140);
        System.out.println("140 rubles are " + euro + " euro."); // 2
        // Ruble - Dollar
        int dollar = rubleToDollar(180);
        System.out.println("180 rubles are " + dollar + " dollar."); // 3
        // Euro - Ruble
        int ruble = euroToRuble(4);
        System.out.println("4 euro are " + ruble + " rubles."); // 280
        // Euro - Dollar
        float dollarByEuro = euroToDollar(90);
        System.out.println("90 euro are " + dollarByEuro + " dollar."); // 99
    }
    private static void test() {
        System.out.println("Testing is:");
        // testing
        // Ruble to Euro
        int in = 140;
        int expected = 2;
        int out = rubleToEuro(in);
        boolean passed = expected == out; // boolean passed = true if (expected == out)
        System.out.println("140 rubles are 2 Euro. Test result : " + passed);
        // Ruble - Dollar
        int in1 = 180;
        int expected1 = 3;
        int out1 = rubleToDollar(in1);
        boolean passed1 = expected1 == out1;
        System.out.println("180 rubles are 3 Dollar. Test result : " + passed1);
        // Euro - Ruble
        int in2 = 4;
        int expected2 = 280;
        int out2 = euroToRuble(in2);
        boolean passed2 = expected2 == out2;
        System.out.println("4 Euro are 280 rubles. Test result : " + passed2);
        // Euro - Dollar
        int in3 = 90;
        int expected3 = 99;
        float out3 = euroToDollar(in3);
        boolean passed3 = expected3 == out3;
        System.out.println("90 Euro are 90 Dollar. Test result : " + passed3);
    }
}