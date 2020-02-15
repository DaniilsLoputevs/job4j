package ru.job4j.exam;

import java.util.ArrayList;

public class ExamTask {

    public boolean contains1(String e1, String e2) {
        var first = convert(e1);
        var second = convert(e2);
        return first.containsAll(second);
    }

    private ArrayList convert(String string) {
        ArrayList<Character> result = new ArrayList();
        for (int i = 0; i < string.length(); i++) {
            result.add(string.charAt(i));
        }
        return result;
    }

    public boolean contains2(String e1, String e2) {
        var one = e1.toCharArray();
        var two = e2.toCharArray();
        var count = 0;
        if (one.length == two.length) {
            for (char symbol : one) {
                if (containsInArr(symbol, two)) {
                    count++;
                }
            }
        }
        return count == one.length;
    }
    private boolean containsInArr(char value, char[] array) {
        var result = false;
        if (array != null) {
            for (char symbol : array) {
                if (symbol == value) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }




}