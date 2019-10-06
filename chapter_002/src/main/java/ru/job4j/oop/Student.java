package ru.job4j.oop;

public class Student {
    public static void main(String[] args) {
        Student petya = new Student();
        Student vasja = new Student();
        String song = "I believe I can fly";

        vasja.music(song);
    }
    public void music(String lyrics) {
        System.out.println("I can sign a song : " + lyrics);
    }
}
