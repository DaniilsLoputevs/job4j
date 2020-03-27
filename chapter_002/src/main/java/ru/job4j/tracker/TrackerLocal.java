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
    /**
     * Добавить заявку в tracker
     * + Присваивает id для новой заявки.
     * @param item новая заявка.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Замена старой заявки по её id, новый завкой(item).
     * После замены, ячейка имее старый id.
     * @param id id старой заявки (для удаление).
     * @param item новая заявка.
     */
    @Override
    public boolean replace(String id, Item item) {
        int index = indexOfId(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return true;
    }

    /**
     * Удалить завку по id.
     * после сдвигает все след. ячейки ближе к началу коллекции.
     * @param id - id заявки для удаление.
     */
    @Override
    public boolean delete(String id) {
        int index = indexOfId(id);
        if (index != -1) {
            items.remove(index);
        }
        return true;
    }

    // Методы findBy...
    /**
     * Найти все заявки.
     * @return Item[] - массив всех заявок.
     */
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Поиск заявки по имени.
     * Т.к. заявок может быть много, метод возвращает ArrayList.
     * @return Item[] - массив всех заявок с одинаковыми именами.
     */
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

    /**
     * Поиск заявки по id.
     * @param id id заявки для возврата.
     * @return Item - нужная заявка.
     */
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

    // Особености методов с возвратом boolean
    //   return (findById(id) != null) ? true : false;

    /**
     * Проверка: Есть ли заявка с таким id в tracker.
     * @param id - id для поиска.
     * @return - true/false
     */
    public boolean containsId(String id) {
        return findById(id) != null;
    }
    /**
     * Проверка: Есть ли заявка с таким name в tracker.
     * @param name - name для поиска.
     * @return - true/false
     */
    public boolean containsName(String name) {
        return findByName(name) != null;
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