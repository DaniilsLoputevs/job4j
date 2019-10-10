package ru.job4j.oop.tracker;

public class Animal {

    public Animal() {
        super();
        System.out.println("load Animal");
    }
    public Animal(String name) {
        System.out.println("load Animal");
    }

    public static void main(String[] args) {
        Tiger japan = new Tiger();
        Tiger white = new Tiger("Белый");
    }

}
