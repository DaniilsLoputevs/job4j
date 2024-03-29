package ru.job4j.threadpool.emails;

import net.jcip.annotations.Immutable;

@Immutable
public class User {
    private final String userName;
    private final String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
