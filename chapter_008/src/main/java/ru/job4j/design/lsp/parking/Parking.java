package ru.job4j.design.lsp.parking;

import ru.job4j.design.lsp.parking.models.Machine;

import java.util.List;

public interface Parking {

    /**
     * Accept Machine.
     *
     * @param machine - Machine.
     * @return - True if machine was accepted.
     */
    boolean accept(Machine machine);

    /**
     * Accept List<Machine>.
     * * If list contains a machine that couldn't accept, it will be miss.
     *
     * @param machineList - List of machines.
     * @return - True if parking can add one or more machines.
     */
    boolean acceptAll(List<Machine> machineList);

    /**
     * Take out machine with {@param number}.
     * @param number - machine number.
     * @return - Left machine.
     */
    Machine leave(String number);

    int occupiedPlace();

    int allPlaces();

}
