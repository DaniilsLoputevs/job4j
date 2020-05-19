package ru.job4j.design.lsp.food.models;

import java.util.Date;

public class Fruit extends Food {
    public Fruit(String name, Date expireDate, Date createDate) {
        super(name, expireDate, createDate);
    }

    public Fruit(String name, Date expireDate, Date createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
