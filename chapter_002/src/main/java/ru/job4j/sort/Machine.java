package ru.job4j.sort;

import java.util.Arrays;

public class Machine {
    private final int[] COINS = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] rsl = new int[100];
        int size = 0;
        int change = money - price;
        for (int i = 0; change >= 1; i++) {
            while (change >= COINS[i]) {
                rsl[size++] = COINS[i];
                change -= COINS[i];
            }
        }
        return Arrays.copyOf(rsl, size);
    }
}