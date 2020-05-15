package ru.job4j.design.lsp.parking.models;

/**
 * Base data model: Machine
 */
public class Machine {
    private String name;
    private MachineType machineType;

    public Machine(String name, MachineType machineType) {
        this.name = name;
        this.machineType = machineType;
    }

    public String getName() {
        return name;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    @Override
    public String toString() {
        return "Machine{"
                + "name='" + name + '\''
                + ", machineType=" + machineType
                + '}';
    }
}
