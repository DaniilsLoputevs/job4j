package ru.job4j.design.lsp;

public interface StoreStrategy {

    boolean accept(Food food, double qualityPercents);

    int size();
}
