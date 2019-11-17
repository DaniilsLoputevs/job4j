package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс менеджер заявок, нужен для создания объектов
 * @author Daniils Loputevs
 * @version $Id$
 * @since 15.11.19
 * Created 15.10.19
 */

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100]; // по умолчания должно быть 100

    /**
     * Указатель ячейки для новой заявки.
     * + Также, указатель заполнености массива.
     */
    private int position = 0;

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ. (далее присваивается заявке(Item item), как id).
     */
    String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }


    // Методы на которых основаны все клаасы в пакете actions
    /**
     * Метод реализаущий добавление заявки в хранилище.
     * + Присваивает id для новой заявки.
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод ищет ячейку по указаному @param id и заменяет её содержимое на @param item.
     * После замены, ячейка имее старый id.
     * @param id id старой заявки (для удаление).
     * @param item новая заявка.
     */
    public void replace(String id, Item item) {
        boolean idExist = false;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                item.setId(id);
                idExist = true;
                break;
            }
        }
        if (!idExist) {
            System.out.println("Такого id не существует.");
        }
    }

    /**
     * Метод принимает id зайавки которую нужно удалить, после двигает все след. ячейки в лево.
     * @param id id заявки (для удаление).
     */
    public void delete(String id) {
        boolean idExist = false;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                System.arraycopy(items, 0, items, 0, position);
                position--;
                idExist = true;
                break;
            }
        }
        if (!idExist) {
            System.out.println("Такого id не существует.");
        }
    }

    /**
     * Метод возвращает все заявки.
     * @return Item[] - массив всех заявок.
     */
    public Item[] findAll() {
        return  Arrays.copyOf(this.items, this.position);
    }

    /**
     * Метод возвращает все заявки совпадающие с указаным именем.
     * @return Item[] - массив всех заявок с одинаковыми именами.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int count = 0;
        for (int i = 0; i < position; i++) {
            if (items[i] != null) {
                if (items[i].getName().equals(key)) {
                    result[count++] = items[i];
                }
            }
        }
        return Arrays.copyOf(result, count);
    }

    /**
     * Метод возвращает заявку по указанному id.
     * @param id id заявки для возврата.
     * @return Item - нужная заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
            result = items[i];
            break;
            }
        }
        if (result == null) {
            System.out.println("Такого id не существует.");
        }
        return result;
    }
}