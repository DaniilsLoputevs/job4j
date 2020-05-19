package ru.job4j.design.lsp.food.models;

import java.util.Date;

public class Bakery extends Food {
    public Bakery(String name, Date expireDate, Date createDate) {
        super(name, expireDate, createDate);
    }

    public Bakery(String name, Date expireDate, Date createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
