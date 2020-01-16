package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private final int[][] matrix;
    private int indexCell = 0;
    private int indexRow = 0;

    // Constructor
    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public boolean hasNext() {
        return matrix.length > indexRow + 1 || matrix[indexRow].length > indexCell + 1;
    }

    @Override
    public Object next() {
        if (matrix[indexRow].length == indexCell) {
            indexRow++;
            indexCell = 0;
        }
        return  matrix[indexRow][indexCell++];
    }
}
