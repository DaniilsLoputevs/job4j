package ru.job4j.oop.tracker;

public class Item {

    public Item(String name) {
        System.out.println("load item");
    }

    public static void main(String[] args) {
        Bug bug = new Bug("Жук или ошибка");
    }
}