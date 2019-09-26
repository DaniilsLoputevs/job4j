package ru.job4j.array;

public class Matrix {
    public static void main(String[] args) {
        // Ручной тест удобен для наглядной проверки результата, так проще понять, что не так.
        Matrix test = new Matrix();
        int[][] arr = test.multiple(5);
        System.out.println();
        test.showX2Array(arr);
    }

    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            int countLeft = i +1;
            for (int j = 0; j < table[i].length; j++) {
                int countRight = j +1;
                int cell = countLeft * countRight;
                if (i == 0) {
                    cell = countRight;
                } else if (j == 0) {
                    cell = countLeft;
                }
                table[i][j] = cell;
            }
        }
        return table;
    }

    // Доп. метод
    // метод вывода массива[][] в консоль.
    private void showX2Array (int[][] X2Array) {
        for (int i = 0; i < X2Array.length; i++) {
            for (int k = 0; k < X2Array[i].length; k++) {
                System.out.print(X2Array[i][k] + " ");
            }
            System.out.println();
        }
    }

}