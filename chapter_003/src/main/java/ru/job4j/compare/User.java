package ru.job4j.compare;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        int result = (this.age > o.age) ? 1 : -1;
        return this.age == o.age ? 0 : result;
    }
}