package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс менеджер заявок, нужен для создания объектов
 * @author Daniils Loputevs
 * @version $Id$
 * @since 21.11.19
 * Created 15.10.19
 */

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList();

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
    public void add(Item item) {
        item.setId(this.generateId());
        items.add(item);
    }

    /**
     * Метод ищет ячейку по указаному @param id и заменяет её содержимое на @param item.
     * После замены, ячейка имее старый id.
     * @param id id старой заявки (для удаление).
     * @param item новая заявка.
     */
    public void replace(String id, Item item) {
        boolean idExist = false;
        int index = 0;
        for (Item itemInList : items) {
            if (itemInList.getId().equals(id)) {
                items.set(index, item);
                item.setId(id);
                idExist = true;
                break;
            }
            index++;
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
        for (Item item : items) {
            if (item.getId().equals(id)) {
                items.remove(findById(id));
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
    public ArrayList<Item> findAll() {
        return new ArrayList<Item>(items);
    }

    /**
     * Метод возвращает все заявки совпадающие с указаным именем.
     * @return Item[] - массив всех заявок с одинаковыми именами.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод возвращает заявку по указанному id.
     * @param id id заявки для возврата.
     * @return Item - нужная заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        if (result == null) {
            System.out.println("Такого id не существует.");
        }
        return result;
    }
}