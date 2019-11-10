package ru.job4j.search;

public class Task {
    private String desc;
    private int priority;

    // Constructor
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    // Getter & Setter
    public String getDesc() {
        return desc;
    }
    public int getPriority() {
        return priority;
    }
}