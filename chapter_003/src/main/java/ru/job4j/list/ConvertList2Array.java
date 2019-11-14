package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows > 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int rowIndex = 0, cellIndex = 0;
        for (int number : list) {
            array[rowIndex][cellIndex] = number;
            cellIndex++;
            if (cellIndex == cells) {
                rowIndex++;
                cellIndex = 0;
            }
        }
        return array;
    }
}