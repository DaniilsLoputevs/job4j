package ru.job4j.array;

public class MatrixCheck {

    public static boolean isWin(char[][] board) {
        //Мульти-return или boolean? - логика такая же, но с boolean строк больше и действий так же.
                if (isWinHorizontal(board)) {
                    return true;
                } else if (isWinVertical(board)) {
                    return true;
                } else return false;
    }
    private static boolean isWinHorizontal (char[][] board) {
        boolean result = false;
        for (int row = 0; row < board.length; row++) {
            int winCount = 0;
            for (int cell = 0; cell < board.length; cell++) {
                if (board[row][cell] == 'X') {
                    winCount++;
                }
                if (winCount == 5) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    private static boolean isWinVertical (char[][] board) {
        boolean result = false;
        for (int row = 0; row < board.length; row++) {
            int winCount = 0;
            for (int cell = 0; cell < board.length; cell++) {
                if (board[cell][row] == 'X') {
                    winCount++;
                }
                if (winCount == 5) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
    // Доп. метод
    // метод вывода массива[][] в консоль.
    private static void showX2Array (char[][] X2Array) {
        for (int i = 0; i < X2Array.length; i++) {
            for (int k = 0; k < X2Array[i].length; k++) {
                System.out.print(X2Array[i][k]);
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
        System.out.println("A board has a winner : " + lose + '\n');
    }
}