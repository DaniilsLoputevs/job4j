package ru.job4j.design.lsp;

public class Context {
    private StoreStrategy storeStrategy;

    public Context(StoreStrategy storeStrategy) {
        this.storeStrategy = storeStrategy;
    }

    public boolean executeStrategy(Food food, double qualityPercents) {
        return storeStrategy.accept(food, qualityPercents);
    }
}
