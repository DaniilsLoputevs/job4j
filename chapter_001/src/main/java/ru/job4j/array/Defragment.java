package ru.job4j.array;

public class Defragment {
    // While can be replaced by for () {}
    public static String[] compress(String[] array) {
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                int j = index + 1;
                while (j < array.length) {
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
                    j++;
                }
            }
//            System.out.print(array[index] + " ");
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