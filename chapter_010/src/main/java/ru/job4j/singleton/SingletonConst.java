package ru.job4j.singleton;

/**
 * Группа: Энергичные.
 * Constant singleton.
 */
public class SingletonConst {
    private static final SingletonConst INSTANCE = new SingletonConst();

    private SingletonConst() {
    }

    public static SingletonConst getInstance() {
        return INSTANCE;
    }

    public Item func(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SingletonConst tracker = SingletonConst.getInstance();
    }
}
