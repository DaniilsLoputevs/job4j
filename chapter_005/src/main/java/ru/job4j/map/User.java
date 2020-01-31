package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

public class User {
    String name;
    int children;
//    Calendar birthday;

    public User(String name, int children) {
        this.name = name;
        this.children = children;
//        this.birthday = birthday;
    }

    // Getters && Setters
    public String getName() {
        return name;
    }
    public int getChildren() {
        return children;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }

    // метод для SimpleMap
    @Override
    public String toString() {
        return  "{ " + children + " }";
    }

}
