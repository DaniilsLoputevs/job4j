package ru.job4j.oop;

public class Battery {
    private int value;
    private int limit;

    public Battery(int size, int limit) {
        this.value = size;
        this.limit = limit;
    }
    public void load(Battery another) {
        int totalValue = this.value + another.value;
        if (totalValue >= another.limit) {
            this.value = limit;
            another.value = totalValue - limit;
        } else {
            this.value = this.value + another.value;
            another.value = 0;
        }
    }

    public static void main(String[] args) {
        Battery first = new Battery(10000, 10000);
        Battery second = new Battery(50, 750);
        System.out.println("first : " + first.value + "/" + first.limit + " --- second : " + second.value + "/" + second.limit);
        second.load(first);
        System.out.println("first : " + first.value + "/" + first.limit + " --- second : " + second.value + "/" + second.limit);

    }
}
