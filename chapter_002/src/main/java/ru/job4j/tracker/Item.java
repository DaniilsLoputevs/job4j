package ru.job4j.tracker;

/**
 * Класс Класс шаблон: item - (заявки)
 * Все заявк хранятся в ArrayList<Item> items в классе Tracker
 * @author Daniils Loputevs
 * @version $Id$
 * @since 06.01.20.
 **/

public class Item {
    private String id;
    private String name;

    // Constructor
    public Item(String name) {
        this.name = name;
    }


    // Getters & Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}