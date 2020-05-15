package ru.job4j.design.lsp.parking.models;

public class Truck extends Machine {
    public Truck(String name) {
        super(name, MachineType.TRUCK);
    }
}
