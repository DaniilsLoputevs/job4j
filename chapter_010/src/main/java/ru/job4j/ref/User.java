package ru.job4j.ref;

import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {
    private int id;
    private String name;

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "User{" + "name= " + name + ", hashCode= " + hashCode() + '\'' + '}';
    }
}