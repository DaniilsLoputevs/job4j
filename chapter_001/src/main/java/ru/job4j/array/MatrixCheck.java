package ru.job4j.array;

public class MatrixCheck {

    public static boolean isWin(char[][] board) {
        boolean result = false;
        for (int row = 0; row < board.length; row++) {
            int winRowCount = 0;
            int winCellCount = 0;
            for (int cell = 0; cell < board.length; cell++) {
                if (board[row][cell] == 'X') {
                    winRowCount++;
                }
                if (board[cell][row] == 'X') {
                    winCellCount++;
                }
                if (winCellCount == board.length || winRowCount == board.length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    // *Доп. метод - метод вывода массива[][] в консоль.
    private static void showX2Array(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                System.out.print(matrix[i][k]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] hasWinVertical = {
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
        };
        boolean win = isWin(hasWinVertical);
        showX2Array(hasWinVertical);
        System.out.println("A board has a winner : " + win + '\n');

        char[][] hasWinHor = {
                {'_', '_', '_', '_', '_'},
                {'X', 'X', 'X', 'X', 'X'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
        };
        boolean winHor = isWin(hasWinHor);
        showX2Array(hasWinHor);
        System.out.println("A board has a winner : " + winHor + '\n');

        char[][] notWin = {
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', 'X', '_', '_', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', 'X', '_', '_'},
        };
        boolean lose = isWin(notWin);
        showX2Array(notWin);
        System.out.println("A board has a winner : " + lose + '\n'); // false is correct
    }
}