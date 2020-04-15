package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс менеджер заявок.
 * @author Daniils Loputevs
 * @version 1.0
 * @since 29.11.19
 * Created 15.10.19
 */

public class TrackerLocal implements Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList();

    /**
     * Генерирует уникальный ключ для заявки.
     * Т.к. у заявки нет уникального поля. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ. (далее присваивается заявке(Item item), как id).
     */
    String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }


    // Методы на которых основаны все клаасы в пакете actions

    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    @Override
    public List<Item> addAll(Item... items) {
        List<Item> result = new ArrayList<>();
        for (var item : items) {
            if (item != null) {
                this.add(item);
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        int index = indexOfId(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOfId(id);
        if (index != -1) {
            items.remove(index);
        }
        return true;
    }

    // Методы findBy...

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }


    @Override
    public List<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }


    @Override
    public Item findById(String id) {
        int index = indexOfId(id);
        return (index != -1) ? items.get(index) : null;
    }

    /**
     * Поиск индекса заявки, по указаному id.
     * @param id - id заявки.
     * @return - индекс заявки.
     */
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


    @Override
    public boolean containsId(String id) {
        return findById(id) != null;
    }

    @Override
    public boolean containsName(String name) {
        return !findByName(name).isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item: items) {
            result.append(item.getName()).append(", ");
        }
        return "TrackerLocal{" + "items = " + result + '}';
    }
}