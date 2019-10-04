package ru.job4j.array;

public class FindLoop {
    // Возвращая индекс указаного элемента в int[]
    // Если элемента нет в массиве, то возвращаем -1.
    public static int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
    // Ищет в диапазоне от start до finish
    public static int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index < finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}