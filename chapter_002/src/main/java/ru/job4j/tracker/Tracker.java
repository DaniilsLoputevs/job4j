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
        item.setId(id);
        items.set(indexOfId(id), item);
    }

    /**
     * Метод принимает id зайавки которую нужно удалить, после двигает все след. ячейки в лево.
     * @param id id заявки (для удаление).
     */
    public void delete(String id) {
        items.remove(indexOfId(id));
    }

    // Методы findBy...
    /**
     * Метод возвращает все заявки.
     * @return Item[] - массив всех заявок.
     */
    public ArrayList<Item> findAll() {
        return new ArrayList<>(items);
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
        return items.get(indexOfId(id));
    }

    public int indexOfId(String id) {
        int index = 0;
        boolean change = false;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                change = true;
                break;
            }
            index++;
        }
        index = (change) ? index : -1;
        return index;
    }

    public boolean contains(String id) {
        // Особености методов с возвратом boolean
//        return (findById(id) != null) ? true : false;
        return findById(id) != null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item: items) {
            result.append(item.getName()).append(", ");
        }
        return "Tracker{" + "items = " + result + '}';
    }
}