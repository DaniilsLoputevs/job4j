package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;

import java.util.List;

/**
 * Control quality class, - put all foods in stores.
 * In Constructor put List with all stores that can hold food.
 */
public interface ControlQuality {
    /**
     * Accept food and put in right store.
     *
     * @param food - food that need to put.
     */
    void accept(Food food);

    /**
     * list.forEach(this::accept);
     *
     * @param list - list with food.
     */
    void acceptAll(List<Food> list);

    /**
     * Resort all food in inside stores.
     */
    void resort();

    /**
     * Return inside Collection with all stores
     *
     * @return - get List stores.
     */
    List<StoreStrategy> getStores();

}
