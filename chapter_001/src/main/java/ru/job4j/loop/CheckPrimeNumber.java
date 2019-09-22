package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
        boolean prime = true;
            if (num <= 10) {
                if ( num == 1) {
                    prime = true;
                }else if (num == 2) {
                    prime = true;
                } else if (num == 3) {
                    prime = true;
                } else if (num == 5) {
                    prime = true;
                } else if (num == 7) {
                    prime = true;
                } else prime = false;
                return prime;
            }
            if (num % 2 == 0) {
                prime = false;
            } else if (num % 3 == 0) {
                prime = false;
            } else if (num % 5 == 0) {
                prime = false;
            } else if (num % 7 == 0) {
                prime = false;
            }
        return prime;
    }
}

