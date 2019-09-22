package ru.job4j.loop;

import ru.job4j.calculator.Car;

public class CarUsage {
    public static void main(String[] args) {
        Car audi = new Car();
        if (!audi.canDrive()) {
            audi.fill(10);
        }
        int km = 0;
        while (audi.canDrive()) {
            audi.drive(1);
            km++;
            if (km == 4) { // Встали в пробку
                audi.drive(1);
                continue;  // Останавливает итерацию и начинает цикл заново. // речь про цикл for
            }
            if (km == 6) { // Дальше ехать невозможно
                break;
            }
        }
        audi.gasInfo();
    }
}
