package ru.job4j.singleton;

/**
 * Группа: Ленивые
 *
 * Его работа стабильна и не влияет не производительность системы.
 */
public class SingletonHolder {
    private SingletonHolder() {
    }

    public static SingletonHolder getInstance() {
        return Holder.INSTANCE;
    }

    public Item func(Item model) {
        return model;
    }

    private static final class Holder {
        private static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static void main(String[] args) {
        SingletonHolder tracker = SingletonHolder.getInstance();
    }
}
