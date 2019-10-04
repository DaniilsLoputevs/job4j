package ru.job4j.loop;

public class Factorial {
    // вычесляет факториал || факториал 5 = 1 * 2 * 3 * 4 * 5 = 120
    public int calc(int n) {
        int result = (n == 0) ? 0 : 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}