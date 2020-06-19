package ru.job4j.design.lsp.food.models;

import java.util.Date;

/**
 * Data model: Food.
 * * can be placed in stores.
 */
public class Food {
    private String name;
    private Date expireDate;
    private Date createDate;
    private double price;
    private double discount;

    public Food(String name, Date expireDate, Date createDate) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = 100;
        this.discount = 0;
    }

    public Food(String name, Date expireDate, Date createDate, double price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

}
