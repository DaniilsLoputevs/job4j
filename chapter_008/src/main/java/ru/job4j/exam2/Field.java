package ru.job4j.exam2;

import java.util.Arrays;

/**
 * Main game field.
 * <p>
 * !!! Field can be ONLY a square!
 */
public class Field {
    /**
     * Main Game field technical.
     */
    private final char[][] field;
    /**
     * Count of free cells on field.
     */
    private int freeCells;
    /**
     * Sign that show that cell is empty.
     */
    private final char emptyCellSign;

    public Field(int size, char emptyCellSign) {
        this.emptyCellSign = emptyCellSign;
        this.freeCells = size * size;
        this.field = createField(size);
    }

    /**
     * Write sign on Game field by coordinates.
     *
     * @param turnParam [0] - row on field. [1] - column on field.
     * @param sign      player's sign.
     */
    public void writeOnField(int[] turnParam, char sign) {
        this.field[turnParam[0]][turnParam[1]] = sign;
        this.freeCells--;
    }

    /**
     * Check: Is cell(by params coordinates) on field free or not?
     *
     * @param row    row in field.
     * @param column column in field.
     * @return true -> if it's empty.
     */
    public boolean isCellFree(int row, int column) {
        return this.field[row][column] == this.emptyCellSign;
    }

    public GameState isWin(char sign) {
        var rsl = isWinVerticalAndHorizontal(sign);
        rsl = (rsl == GameState.CONTINUE)
                ? isWinElse(sign, 0, field.length - 1, 1, -1) : rsl;
        rsl = (rsl == GameState.CONTINUE)
                ? isWinElse(sign, 0, 0, 1, 1) : rsl;
        if (rsl == GameState.CONTINUE && this.freeCells == 0) {
            rsl = GameState.DRAW;
        }
        return rsl;
    }

    /* ---------------- Private Methods -------------- */


    /**
     * Check is win Vertical or Horizontal.
     *
     * @param sign player's sign.
     * @return GameState.WIN || GameState.CONTINUE
     */
    private GameState isWinVerticalAndHorizontal(char sign) {
        GameState result = GameState.CONTINUE;
        for (int row = 0; row < field.length; row++) {
            int winRowCount = 0;
            int winCellCount = 0;
            for (int cell = 0; cell < field.length; cell++) {
                if (field[row][cell] == sign) {
                    winRowCount++;
                }
                if (field[cell][row] == sign) {
                    winCellCount++;
                }
                if (winCellCount == field.length || winRowCount == field.length) {
                    result = GameState.WIN;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Check is win Non-Vertical or Non-Horizontal.
     *
     * @param sign        player's sign.
     * @param startRow    -
     * @param startColumn -
     * @param deltaRow    -
     * @param deltaColumn -
     * @return GameState.WIN || GameState.CONTINUE
     */
    public GameState isWinElse(char sign, int startRow, int startColumn, int deltaRow, int deltaColumn) {
        GameState result = GameState.CONTINUE;
        int winCount = 0;
        for (int index = 0; index != field.length; index++) {
            if (field[startRow][startColumn] == sign) {
                winCount++;
            }
            if (winCount == field.length) {
                result = GameState.WIN;
                break;
            }
            startRow += deltaRow;
            startColumn += deltaColumn;
        }
        return result;
    }

    /**
     * Create filed by size.{@param size} Fill by {@code emptyCellSign}.
     *
     * @param size count of row and columns.
     * @return new Game field.
     */
    private char[][] createField(int size) {
        var newField = new char[size][size];
        for (char[] chars : newField) {
            Arrays.fill(chars, this.emptyCellSign);
        }
        return newField;
    }

    /**
     * * Checked about copy of field. ->
     * We have inside double array(primitive type) and use getArr() >> we have 2 Unlinked arrays. >>
     * It's mean that we can change 2nd and 1st WILL NOT change.
     * <p>
     * * Special for {@code class Game}.
     *
     * @return This is getter.
     */
    public char[][] getField() {
        return field;
    }

    public int getSize() {
        return this.field.length;
    }
}