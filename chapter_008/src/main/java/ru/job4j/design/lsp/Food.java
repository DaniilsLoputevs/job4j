package ru.job4j.design.lsp;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Base data model.
 */
public class Food {
    private String name;
    private Calendar expireDate;
    private Calendar createDate;
    private double price;
    private double discount;

    public Food(String name, Calendar expireDate) {
        var createTime = new GregorianCalendar();
        createTime.add(Calendar.MONTH, -4);

        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createTime;
    }

    public Food(String name, Calendar expireDate, Calendar createDate, double price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


}
