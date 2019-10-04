package ru.job4j.loop;

public class Board {
    //нарисовать доску в консоль
    public static String paintString(int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int cell = 0; cell < width; cell++) {
                int sum = cell + row;
                if (sum % 2 == 0) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    public static void paint(int width, int height) {
        for (int row = 0; row < height; row++) {
            for (int cell = 0; cell < width; cell++) {
                int sum = cell + row;
                if (sum % 2 == 0) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // Не особо хорошо оставлять за собой ручные тесты.
//    public static void main(String[] args) {
//        paint(3, 3);
//        System.out.println();
//        paint(5, 5);
//    }
    
}