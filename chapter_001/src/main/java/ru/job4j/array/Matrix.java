package ru.job4j.array;

public class Matrix {
    public static void main(String[] args) {
        // Ручной тест удобен для наглядной проверки результата, так проще понять, что не так.
        Matrix test = new Matrix();
        int[][] arr = test.multiple(5);
        System.out.println();
        test.shoMatrix(arr);
        // multipleV2
        int[][] arr1 = test.multipleV2(5);
        System.out.println();
        test.shoMatrix(arr1);
    }
    // Показывает таблицу умнажения от 1 до size
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            int countLeft = i + 1;
            for (int j = 0; j < table[i].length; j++) {
                int countRight = j + 1;
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
    public int[][] multipleV2(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

    // *Доп. метод - метод вывода массива[][] в консоль.
    private void shoMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
    }
}