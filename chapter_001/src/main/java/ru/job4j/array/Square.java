package ru.job4j.array;

public class Square {
    // return square from 1 to bound in int[]
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        int index = 0;
        for (int i = 1; i <= bound; i++) {
            rst[index] = i * i;
            index++;
        }
        return rst;
    }
}