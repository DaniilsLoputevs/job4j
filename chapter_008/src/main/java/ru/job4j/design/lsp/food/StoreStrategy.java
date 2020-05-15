package ru.job4j.design.lsp.food;

public interface StoreStrategy {

    /**
     * try to accept food in store.
     *
     * @param food            - object food.
     * @param qualityPercents - Badly the food is spoiled in percents value.
     * @return - true if inside predicate say true.
     */
    boolean accept(Food food, double qualityPercents);

    /**
     * @return - how many food was accepted in store.
     */
    int size();
}
