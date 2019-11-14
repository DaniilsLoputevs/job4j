package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        int row = 0, cell = 0;

        for (int i = 0; i <= list.size(); i++) {
            list.add(array[row][cell]);
            cell++;
            if (cell == array[row].length) {
                row++;
                cell = 0;
            }
            if (row == array.length) { break; }
        }
        return list;
    }
}