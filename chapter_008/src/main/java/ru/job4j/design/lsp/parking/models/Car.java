package ru.job4j.design.lsp.parking.models;

public class Car extends Machine {
    public Car(String name) {
        super(name, MachineType.CAR);
    }
}
