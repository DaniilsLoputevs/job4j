package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;


public class Warehouse extends BaseStore {

    @Override
    public boolean accept(Food food, double qualityPercents) {
        var result = false;
        if (qualityPercents <= 25.0) {
            result = super.store.add(food);
        }
        return result;
    }

}