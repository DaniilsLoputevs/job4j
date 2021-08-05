package ru.job4j.singleton;

/**
 * Группа: Энергичные.
 * Enum Singleton.
 *
 * Объект enum создается при загрузки класса
 * и безопасно публикуется всех клиентам.
 */
public enum SingletonEnum {
    INSTANCE;

    public Item func(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SingletonEnum tracker = SingletonEnum.INSTANCE;
    }
}
