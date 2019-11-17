package ru.job4j.tracker;

/**
 * Класс нужен для создания объектов(заявок).
 * Все заявк хранятся ArrayList<Item> items в классе Tracker
 * @author Daniils Loputevs
 * @version $Id$
 * @since 17.11.19
 **/

public class Item {
    private String id;
    private String name;

    /**
     * Основной конструктор
     * @param name - имя заявки
     **/
    public Item(String name) {
        this.name = name;
    }


     // Getters & Setters
    /**
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * Set id
     **/
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Set name
     **/
    public void setName(String name) {
        this.name = name;
    }
}