package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
//        for (int i = 2)
        boolean prime = true;
        if (num % 2 == 0 ) {
            prime = false;
        }
        return prime;
    }
}