package ru.job4j.loop;

public class PrimeNumbers {
    public int calc(int finish) {
        CheckPrimeNumber check = new CheckPrimeNumber();
        int count = 0;
        for (int i = 2; i <= finish; i++) {
            boolean isPrimeNumber = check.check(i);
            if (isPrimeNumber) {
                count++;
            }
        }
        return count;
    }
}
