package ru.job4j.bank;

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public boolean transferTo(Account dst, double amount) {
        boolean result = false;
        if (checkAmount(amount)) {
            this.value -= amount;
            dst.value += amount;
            result = true;
        }
        return result;
    }

    public boolean checkAmount(double amount) {
        // Особености boolean
//        return (this.value >= amount) ? true : false;
        return this.value >= amount;
    }

    // Getters & Setters
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "Account{" + "value="
                + value + ", requisites='"
                + requisites + '\'' + '}';
    }
}