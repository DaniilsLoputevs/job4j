package ru.job4j.array;

public class Defragment {
    public static String[] compress(String[] array) {
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                // Здесь нужен цикл while
                int j = index + 1;  // looks like a part of "for" loop
                while (j < array.length) {  // looks like a part of "for" loop
                    if (array[j] == null) {
                        j++;
                        continue;
                    }
                    if (array[j] != null) {
                        String temp = array[index];
                        array[index] = array[j];
                        array[j] = temp;
                        break;
                    }
                    j++;  // looks like a part of "for" loop
                }
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();

        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }
}