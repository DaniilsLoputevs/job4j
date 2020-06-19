package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;

/**
 * Class don't use.
 */
public class Context {
    private StoreStrategy storeStrategy;

    public Context(StoreStrategy storeStrategy) {
        this.storeStrategy = storeStrategy;
    }

    public boolean executeStrategy(Food food, double qualityPercents) {
        return storeStrategy.accept(food, qualityPercents);
    }
}
