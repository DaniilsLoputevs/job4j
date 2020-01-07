package ru.job4j.exam;

class Holder {
    public String key; // Имя секции
    public String value; // Имя студента

    Holder(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
