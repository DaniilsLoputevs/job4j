package ru.job4j.poly;

public class Bus implements Transport {
    private int passengers;
    private double fuel;

    @Override
    public void move() {
        System.out.println("Следующая остановка, Краснодар!");
    }

    @Override
    public void passengers(int passengers) {
        System.out.println("Пассажиров: " + passengers);
        this.passengers = passengers;
    }

    @Override
    public double fillUp(double cost) {
        this.fuel = cost / 103; // Стоимость бензина
        System.out.println("Вы заправились на рублей " + cost + " = " + fuel + " литров");
        return cost;
    }

    public static void main(String[] args) {
        Bus ikarus = new Bus();
        ikarus.fillUp(20000);
        ikarus.passengers(40);
        ikarus.move();
    }
}


