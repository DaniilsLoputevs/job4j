package ru.job4j.design.lsp.parking;

import daniils.StringHelper;
import ru.job4j.design.lsp.parking.models.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Parking.
 * <p>
 * * truckExtraPlaces - how many Trucks can be placed in Car's place.
 * * truckExtraPlaces - counter how many Truck's Extra free places have left.
 */
public class DefaultParking implements Parking {
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

    public DefaultParking(int carPlaces, int truckPlaces, int truckExtraPlaces) {
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
    public boolean accept(Machine machine) {
        var type = machine.getHeight();
        var result = false;

        if (type <= 180) {
            result = addCar(machine);
        } else if (type >= 210) {
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

    @Override
    public Machine leave(String number) {
        return parking.remove(findIndexByNumber(number));
    }

    private int findIndexByNumber(String number) {
        int count = 0;
        for (var machine : this.parking) {
            if (number.equals(machine.getCarNumber())) {
                break;
            }
            count++;
        }
        return count;
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
                "DefaultParking",
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
