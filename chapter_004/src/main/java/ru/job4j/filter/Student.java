package ru.job4j.filter;

public class Student {
   private String name;
   private int score;

    public Student(int score) {
        this.score = score;
    }
    public Student(String name) {
        this.name = name;
    }
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
