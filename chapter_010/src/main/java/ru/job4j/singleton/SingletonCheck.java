package ru.job4j.singleton;

/**
 * Группа: Ленивые.
 * Single checked locking.
 *
 * Инициализация и проверка экземпляра происходит
 * внутри критической секции. Этот шаблон деградирует производительность.
 * !!! - Использовать этот шаблон не рекомендуется.
 */
public class SingletonCheck {

    private static SingletonCheck instance;

    private SingletonCheck() {
    }

    public static synchronized SingletonCheck getInstance() {
        if (instance == null) {
            instance = new SingletonCheck();
        }
        return instance;
    }

    public Item func(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SingletonCheck tracker = SingletonCheck.getInstance();
    }
}
