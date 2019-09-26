package ru.job4j.array;

public class EndsWith {
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        int postIndex = post.length - 1;
        // проверить, что массив word имеет последние элементы одинаковые с post
        for (int i = word.length - 1; i >= 0 ; i--) {
            if (word[i] != post[postIndex]) {
                result = false;
                break;
            }
            if (postIndex == 0) {
                break;
            }
            postIndex--;
        }
        return result;
    }
}