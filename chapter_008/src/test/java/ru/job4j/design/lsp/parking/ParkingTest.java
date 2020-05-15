package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.parking.models.Car;
import ru.job4j.design.lsp.parking.models.Machine;
import ru.job4j.design.lsp.parking.models.Truck;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParkingTest {
    private List<Machine> machineList;
    private Parking parking;

    @Before
    public void setUp() {
        machineList = List.of(
                new Car("firstC"),
                new Car("SecondC"),
                new Truck("firstT"),
                new Truck("SecondT"),
                new Truck("thirdT"),
                new Truck("forthT"),
                new Truck("fifthT"),
                new Truck("sixth"),
                new Truck("seventh")
        );
        parking = new DefaultParking(20, 5, 1);
    }

    @Test
    public void devRun() {
        parking.acceptAll(machineList);

        assertEquals(25, parking.allPlaces());
        assertEquals(8, parking.occupiedPlace());

        var tempParking = (DefaultParking) parking;
        assertEquals(17, tempParking.getCarPlacesFree());
        assertEquals(0, tempParking.getTruckPlacesFree());
        assertEquals(0, tempParking.getTruckExtraPlacesFree());
    }
}