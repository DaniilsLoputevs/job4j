package ru.job4j.oop;

public class DummyDic {
    String engToRus(String eng) {
        return "Неизвестное слово - " + eng;
    }

    public static void main(String[] args) {
        DummyDic translator = new DummyDic();
        String one = translator.engToRus("go");
        String two = translator.engToRus("walk");
        String three = translator.engToRus("run");
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }
}
