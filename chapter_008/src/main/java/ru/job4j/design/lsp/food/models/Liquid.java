package ru.job4j.design.lsp.food.models;

import java.util.Date;

public class Liquid extends Food {
    public Liquid(String name, Date expireDate, Date createDate) {
        super(name, expireDate, createDate);
    }

    public Liquid(String name, Date expireDate, Date createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
