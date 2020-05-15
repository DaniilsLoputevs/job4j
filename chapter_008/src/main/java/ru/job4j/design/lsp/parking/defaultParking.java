package ru.job4j.design.lsp.parking;

import daniils.StringHelper;
import ru.job4j.design.lsp.parking.models.Machine;
import ru.job4j.design.lsp.parking.models.MachineType;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Parking.
 *
 * * truckExtraPlaces - how many Trucks can be placed in Car's place.
 * * truckExtraPlaces - counter how many Truck's Extra free places have left.
 */
public class defaultParking implements Parking {
    // limits
    private int carPlaces;
    private int truckPlaces;
    private int truckExtraPlaces;
    // counters
    private int carPlacesFree;
    private int truckPlacesFree;
    private int truckExtraPlacesFree;
    // collection
    private List<Machine> parking;

    public defaultParking(int carPlaces, int truckPlaces, int truckExtraPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
        this.truckExtraPlaces = truckExtraPlaces;

        this.carPlacesFree = carPlaces;
        this.truckPlacesFree = truckPlaces;
        this.truckExtraPlacesFree = truckExtraPlaces;

        this.parking = new ArrayList<>(carPlaces + truckPlaces);
    }

    /**
     * * I divided this method because if logic was hard for read.
     *
     * @param machine - machine that it try to accept.
     * @return - True if machine accepted.
     */
    @Override
    public boolean accept(Machine machine) {
        var type = machine.getMachineType();
        var result = false;

        if (type.equals(MachineType.CAR)) {
            result = addCar(machine);
        } else if (type.equals(MachineType.TRUCK)) {
            result = addTruck(machine);
        }
        return result;
    }

    @Override
    public boolean acceptAll(List<Machine> machineList) {
        var result = false;
        if (occupiedPlace() < allPlaces()) {
            machineList.forEach(this::accept);
            result = true;
        }
        return result;
    }

    private boolean addCar(Machine machine) {
        var result = false;
        if (carPlacesFree != 0) {
            result = this.parking.add(machine);
            carPlacesFree--;
        }
        return result;
    }

    private boolean addTruck(Machine machine) {
        var result = false;
        if (truckPlacesFree != 0) {
            result = this.parking.add(machine);
            truckPlacesFree--;

        } else if (carPlacesFree != 0 && truckExtraPlacesFree != 0) {
            result = this.parking.add(machine);
            truckExtraPlacesFree--;
            carPlacesFree--;
        }
        return result;
    }

    public int getCarPlacesFree() {
        return carPlacesFree;
    }

    public int getTruckPlacesFree() {
        return truckPlacesFree;
    }

    public int getTruckExtraPlacesFree() {
        return truckExtraPlacesFree;
    }

    @Override
    public int occupiedPlace() {
        return this.parking.size();
    }

    @Override
    public int allPlaces() {
        return carPlaces + truckPlaces;
    }

    @Override
    public String toString() {
        var temp = StringHelper.separateLines(List.of(
                "defaultParking",
                "carPlaces; " + carPlaces,
                "truckPlaces= " + truckPlaces,
                "truckExtraPlaces= " + truckExtraPlaces,
                "carPlacesFree= " + carPlacesFree,
                "truckPlacesFree= " + truckPlacesFree,
                "truckExtraPlacesFree= " + truckExtraPlacesFree,
                "parking= " + parking
        ));
        return StringHelper.mergeToOne(temp);
    }
}
