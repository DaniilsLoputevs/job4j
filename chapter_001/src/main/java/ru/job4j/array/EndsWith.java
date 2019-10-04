package ru.job4j.array;

public class EndsWith {
    // проверить, что массив word имеет последние элементы одинаковые с post
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        int postIndex = post.length - 1;
        for (int i = word.length - 1; i >= 0; i--) {
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
    public static boolean endsWithV2(char[] word, char[] post) {
        boolean result = true;
        for (int i = 0; i < post.length; i++) {
            if (word[word.length - 1 - i] != post[post.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}