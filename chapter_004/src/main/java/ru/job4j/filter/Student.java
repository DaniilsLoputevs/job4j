package ru.job4j.filter;

public class Student {
   private int score;
   private String name;


    public Student(int score) {
        this.score = score;
    }
    public Student(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
