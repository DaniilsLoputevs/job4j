package ru.job4j.loop;

public class Slash {
    // Крест в пссевдо-графике
    public static void draw(int size) {
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                int indexForLeft = row * 2;
                int indexForRight = size - 1; // 2 & 4
                if (row + cell == indexForLeft) {
                    System.out.print("0");
                } else if (row + cell == indexForRight) {
                    System.out.print("0");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    // row = ряд
    // cell = клетка
    public static void main(String[] args) {
        System.out.println("Draw by 3");
        draw(3);
        System.out.println("Draw by 5");
        draw(5);
        System.out.println("Draw by 7");
        draw(7);
    }
}