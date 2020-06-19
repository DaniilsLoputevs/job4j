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
                new Car("firstC", "100"),
                new Car("SecondC", "200"),
                new Truck("firstT", "300"),
                new Truck("SecondT", "400"),
                new Truck("thirdT", "500"),
                new Truck("forthT", "600"),
                new Truck("fifthT", "700"),
                new Truck("sixth", "800"),
                new Truck("seventh", "900")
        );
        parking = new DefaultParking(20, 5, 1);
    }

    @Test
    public void acceptTest() {
        parking.acceptAll(machineList);

        assertEquals(25, parking.allPlaces());
        assertEquals(8, parking.occupiedPlace());

        var tempParking = (DefaultParking) parking;
        assertEquals(17, tempParking.getCarPlacesFree());
        assertEquals(0, tempParking.getTruckPlacesFree());
        assertEquals(0, tempParking.getTruckExtraPlacesFree());
    }

    @Test
    public void leaveTest() {
        parking.acceptAll(machineList);

        var temp = parking.leave("100");
        assertEquals(7, parking.occupiedPlace());
        assertEquals("100", temp.getCarNumber());
    }
}