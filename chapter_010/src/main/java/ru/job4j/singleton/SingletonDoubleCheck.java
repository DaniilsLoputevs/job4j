package ru.job4j.singleton;

/**
 * Группа: Ленивые.
 * Double checked locking.
 *
 * Поле экземпляра обозначено volatile.
 * Это обеспечит решение проблемы видимости,после инициализации поля.
 *
 * Первая проверка экземпляра идет до блока синхронизации,
 * что позволяет улучить скорость работы по сравнению с single checked locking реализацией.
 *
 * В критической секции происходит инициализация экземпляра и запись его в переменную.
 *
 * !!! - Этот шаблон использовать не рекомендуется.
 * !!! - Он уменьшает производительность системы при многопроцессорном окружении.
 */
public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck instance;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }

    public Item func(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SingletonDoubleCheck tracker = SingletonDoubleCheck.getInstance();
    }
}
