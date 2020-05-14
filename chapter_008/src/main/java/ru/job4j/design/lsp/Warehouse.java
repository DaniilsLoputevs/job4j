package ru.job4j.design.lsp;

import java.util.ArrayList;

public class Warehouse implements StoreStrategy {
    private ArrayList<Food> store = new ArrayList<>();

    @Override
    public boolean accept(Food food, double qualityPercents) {
        var result = false;
        if (qualityPercents <= 25.0) {
            result = store.add(food);
        }
        return result;
    }

    @Override
    public int size() {
        return this.store.size();
    }
}