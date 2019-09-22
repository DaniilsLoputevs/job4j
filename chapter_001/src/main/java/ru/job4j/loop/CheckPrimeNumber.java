package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
        boolean prime = true;
            for (int j = 2; j < num; j++) {
                if (num % j == 0) {
                    prime = false;
                    break;
                }
            }
        return prime;
    }
}

