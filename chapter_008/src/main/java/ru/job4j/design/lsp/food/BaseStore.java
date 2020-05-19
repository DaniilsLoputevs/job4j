package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Base for implements of StoreStrategy.
 */
public abstract class BaseStore implements StoreStrategy {
    ArrayList<Food> store = new ArrayList<>();

    @Override
    public boolean accept(Food food, double qualityPercents) {
        return false;
    }

    @Override
    public List<Food> takeOutAll() {
        var tempStore = new ArrayList<>(this.store);
        this.store.clear();
        return tempStore;
    }

    @Override
    public boolean justPush(Food food) {
        return this.store.add(food);
    }

    @Override
    public int size() {
        return this.store.size();
    }
}
