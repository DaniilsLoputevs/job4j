package ru.job4j.generic;

public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        super(new SimpleArray<>(size));
    }

}
