package ru.job4j.tracker;

import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                result = true;
                break;
            }
        }

        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = null;
                result = true;
                index = i;
                break;
            }
        }
        for (; index < items.length - 1; index++) {
            items[index] = items[index + 1];
        }
        return result;
    }

    public Item[] findAll() {
        int length = 0;
        int index = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                length++;
            }
        }
        Item[] result = new Item[length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                result[index] = items[i];
                i++;
            }
        }
        return result;
    }

    public Item[] findByName(String key) {
        int length = 0;
        int index = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getName().equals(key)) {
                    length++;
                }
            }
        }
        Item[] result = new Item[length + 1];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getName().equals(key)) {
                    result[index] = items[i];
                    index++;
                }
            }
        }
        return result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

}