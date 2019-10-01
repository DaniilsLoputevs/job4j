package ru.job4j.extra;

public class Merge {

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (leftIndex == left.length) {
                result[i] =  right[rightIndex];
                rightIndex++;
            } else if (rightIndex == right.length) {
                result[i] = left[leftIndex];
                leftIndex++;
            } else if (left[leftIndex] <= right[rightIndex]) {
                result[i] = left[leftIndex];
                leftIndex++;
            } else if (left[leftIndex] >= right[rightIndex]) {
                result[i] =  right[rightIndex];
                rightIndex++;
            }
        }
        return result;
    }

    // Доп. метод
    // метод вывода массива[][] в консоль.
    private void showIntsArray(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5},
                new int[] {2, 4});
        process.showIntsArray(rsl);
    }
}