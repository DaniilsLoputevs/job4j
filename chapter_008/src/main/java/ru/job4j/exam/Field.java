package ru.job4j.exam;

import ru.job4j.exam.io.Output;

/**
 * Main game field.
 * Field can be only a square!
 * <p>
 * var:
 * field - main Game field technical.
 * freeCells - count of free cells on field.
 * emptyCellSign - sign that show that cell is empty.
 */
public class Field {
    private char[][] field;
    private int freeCells;
    private char emptyCellSign;

    public Field(int size, char emptyCellSign) {
        this.emptyCellSign = emptyCellSign;
        this.freeCells = size * size;
        this.field = createField(size);
    }

    /**
     * Show Game field to player.
     *
     * @param output - Player's output.
     */
    public void showField(Output output) {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                output.accept(this.field[i][j]);
            }
            output.accept(System.lineSeparator());
        }
    }

    /**
     * Write sign on Game field by coordinates.
     *
     * @param row    - row in field.
     * @param column - column in field.
     * @param sign   - player's sign.
     */
    public void writeOnField(int row, int column, char sign) {
        this.field[row][column] = sign;
        this.freeCells--;
    }

    /**
     * Check: Is cell(by params coordinates) on field free or not?
     *
     * @param row    - row in field.
     * @param column - column in field.
     * @return - true -> if it's empty.
     */
    public boolean isCellFree(int row, int column) {
        return this.field[row][column] == this.emptyCellSign;
    }


    /**
     * Create filed by {@param size}. Fill by {@code emptyCellSign}.
     *
     * @param size - count of row and columns.
     * @return -  new Game field.
     */
    private char[][] createField(int size) {
        var newField = new char[size][size];
        for (int i = 0; i < newField.length; i++) {
            for (int j = 0; j < newField[i].length; j++) {
                newField[i][j] = this.emptyCellSign;
            }
        }
        return newField;
    }

    public char[][] getField() {
        return field;
    }

    public int getFreeCells() {
        return freeCells;
    }
}