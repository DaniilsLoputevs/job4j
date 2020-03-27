package ru.job4j.tracker;

import java.util.List;

/** Интерфейс для менеджера заявок.
 * 2 имплементации: {@code TrackerSQL} и {@code TrackerLocal}
 * 1) - работает с базой
 * 2) - работает с коллекций.
 * {@code TrackerSQL} - нахотидся в {@code chapter_007}
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 25.03.20
 */
interface Tracker {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);

    boolean containsId(String id);
    boolean containsName(String name);
}