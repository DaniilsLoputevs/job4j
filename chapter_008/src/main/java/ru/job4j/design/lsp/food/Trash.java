package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;


public class Trash extends BaseStore {

    @Override
    public boolean accept(Food food, double qualityPercents) {
        var result = false;
        if (qualityPercents >= 75.0) {
            result = super.store.add(food);
        }
        return result;
    }

}