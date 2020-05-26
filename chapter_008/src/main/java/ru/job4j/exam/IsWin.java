package ru.job4j.exam;

public class IsWin {

    public static boolean isWin(Field field, char sign) {
        boolean result = false;
        if (field.getFreeCells() != 0) {
            result = isWinLogic(field.getField(), sign);
        }

        return result;
    }

    private static boolean isWinLogic(char[][] table, char sign) {
        boolean result = false;
        for (int row = 0; row < table.length; row++) {
            int winRowCount = 0;
            int winCellCount = 0;
            for (int cell = 0; cell < table.length; cell++) {
                if (table[row][cell] == sign) {
                    winRowCount++;
                }
                if (table[cell][row] == sign) {
                    winCellCount++;
                }
                if (winCellCount == table.length || winRowCount == table.length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
