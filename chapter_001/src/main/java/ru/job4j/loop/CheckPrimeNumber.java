package ru.job4j.loop;

public class CheckPrimeNumber {
    // проверяет это число - простое или нет
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

