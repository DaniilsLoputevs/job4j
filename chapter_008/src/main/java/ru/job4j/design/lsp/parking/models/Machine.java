package ru.job4j.design.lsp.parking.models;

/**
 * Base data model: Machine
 */
public class Machine {
    private String name;
    private String carNumber;
    private int height;

    public Machine(String name, String carNumber, int height) {
        this.name = name;
        this.carNumber = carNumber;
        this.height = height;
    }


    public String getName() {
        return name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public String toString() {
        return "Machine{"
                + "name='" + name + '\''
                + ", carNumber=" + carNumber
                + ", height=" + height
                + '}';
    }
}
