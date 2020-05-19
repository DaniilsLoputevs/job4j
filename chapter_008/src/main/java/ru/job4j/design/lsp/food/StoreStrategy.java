package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;

import java.util.List;

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
     * Force push food into this store without quality check.
     *
     * @param food - push this food.
     * @return - true.
     */
    boolean justPush(Food food);

    /**
     * Take out all food from this store to {@return}.
     *
     * @return - List<Food>.
     */
    List<Food> takeOutAll();

    /**
     * @return - how many food was accepted in store.
     */
    int size();
}
